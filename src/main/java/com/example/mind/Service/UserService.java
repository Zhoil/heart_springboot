package com.example.mind.Service;

import com.example.mind.DTO.LoginResponse;
import com.example.mind.DTO.UserResDTO.UserDTO;
import com.example.mind.DTO.UserResDTO.UserDTOResponse;
import com.example.mind.Entries.TestedUser;
import com.example.mind.Entries.User;
import com.example.mind.enumtion.Role;
import com.example.mind.repository.TestedUserRepository;
import com.example.mind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestedUserRepository testedUserRepository;


    // 登录
    public LoginResponse login(String username, String password) {

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if(user.getRole().equals(Role.user)) {
                if(!user.getPassword().equals(password)) return LoginResponse.pass_error();
                Optional<TestedUser> testedUserOptional = testedUserRepository.findTestedUserByTestedUserId(user.getUserId());
                if(testedUserOptional.isPresent()){
                    TestedUser testedUser = testedUserOptional.get();
                    return new LoginResponse(user.getUserId(),
                                            testedUser.getName(),
                                            user.getRole(),
                                            testedUser.getGender(),
                                            testedUser.getAge(),
                                            testedUser.getIdCard(),
                                            testedUser.getPhone(),
                                            true);
                } else {
                    return new LoginResponse(user.getUserId(),
                            user.getUsername(),
                            user.getRole(),
                            true);
                }
            } else if(user.getPassword().equals(password)) {
                return new LoginResponse(user.getUserId(),
                        user.getUsername(),
                        user.getRole(),
                        true);
            }
            return LoginResponse.pass_error();
        }
        return LoginResponse.error();
    }

    // 查询测试者名下的所有被测试人员
    public UserDTOResponse query_testees(String id) {
        List<TestedUser> testedUsers = testedUserRepository.findAllByUser_UserId(id);
        if( !testedUsers.isEmpty() ) {

            List<UserDTO> usersList = testedUsers.stream()
                    .map(arr -> new UserDTO(arr.getTestedUserId(),
                                            arr.getPhone(),
                                            arr.getIdCard(),
                                            arr.getName()))
                    .collect(Collectors.toList());
            return new UserDTOResponse(usersList);

        }

        return null;
    }

    // 更新被测人信息
    @Transactional
    public void updateUserInfo(String id, String name, String idcard, String phone) {
        // 直接根据 testeduser_id（即 id）查询
        TestedUser testedUser = testedUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("被测人信息不存在, id: " + id));
        // 更新姓名、身份证号和电话
        testedUser.setName(name);
        testedUser.setIdCard(idcard);
        testedUser.setPhone(phone);
        testedUserRepository.save(testedUser);
    }


}
