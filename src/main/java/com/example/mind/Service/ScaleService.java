package com.example.mind.Service;

import com.example.mind.DTO.CreateScaleDTO.CreateScaleRequest;
import com.example.mind.DTO.CreateScaleDTO.OptionRequest;
import com.example.mind.DTO.CreateScaleDTO.QuestionRequest;
import com.example.mind.DTO.SclaeDTO.ScaleGetResponse;
import com.example.mind.DTO.SclaeDTO.ScaleQuesOptionsDTO;
import com.example.mind.DTO.SclaeDTO.ScaleQuestionsDTO;
import com.example.mind.DTO.SclaeDTO.ScaleSimpleDTO;
import com.example.mind.DTO.SclaeDTO.Stats.OptionStatsDTO;
import com.example.mind.DTO.SclaeDTO.Stats.QuestionStatsDTO;
import com.example.mind.DTO.SclaeDTO.Stats.ScaleStatsDTO;
import com.example.mind.Entries.*;
import com.example.mind.Excption.ResourceNotFoundException;
import com.example.mind.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScaleService {

    @Autowired
    private ScaleRepository scaleRepository;

    @Autowired
    private TestAnswersRepository testAnswersRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private FactorRepository factorRepository;

    // 获取所有量表
    public List<ScaleSimpleDTO> getAllScales() {
        return scaleRepository.findAllScalesWithCategory();
    }

    // 通过量表id获取量表详情
    public ScaleGetResponse getScaleDetail(String scaleId) {
        Scale scale = scaleRepository.findByIdWithDetails(scaleId)
                .orElseThrow(() -> new RuntimeException("Scale not found"));

        return ScaleGetResponse.builder()
                .id(scale.getScaleId())
                .name(scale.getTitle())
                .managerId(scale.getManagerId())
                .questions(scale.getQuestions().stream()
                        .map(question -> ScaleQuestionsDTO.builder()
                                .id(question.getQuestionId())
                                .scaleId(scale.getScaleId())
                                .description(question.getQuestionText())
                                .factorIds(question.getFactors().isEmpty() ? null : Collections.singletonList(question.getFactors().get(0).getFactorId()))
                                .options(question.getOptions().stream()
                                        .map(option -> ScaleQuesOptionsDTO.builder()
                                                .id(option.getOptionId())
                                                .quetsionId(question.getQuestionId())
                                                .score(option.getScore())
                                                .description(option.getOptionText())
                                                .build())
                                        .collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    // 通过量表id获取量表详情 优化后
    public ScaleGetResponse getScaleDetails(String scaleId) {
        // 查询量表信息
        Optional<Scale> scaleOptional = scaleRepository.findById(scaleId);
        if (scaleOptional.isEmpty()) {
            throw new ResourceNotFoundException("Scale not found for id: " + scaleId);
        }
        Scale scale = scaleOptional.get();

        // 查询量表的所有问题
        List<Question> questions = questionRepository.findQuestionsByScaleId(scaleId);

        // 获取问题ID列表
        List<String> questionIds = questions.stream()
                .map(Question::getQuestionId)
                .collect(Collectors.toList());

        // 查询问题的所有选项
        List<Option> options = optionRepository.findOptionsByQuestionIds(questionIds);

        // 查询问题的所有因子
        List<Factor> factors = factorRepository.findFactorsByQuestionIds(questionIds);

        // 将数据组装成DTO
        List<ScaleQuestionsDTO> questionDTOs = questions.stream()
                .map(q -> {
                    // 获取该问题对应的因子ID集合
                    List<String> factorIds = factors.stream()
                            .filter(f -> f.getQuestions().contains(q))
                            .map(Factor::getFactorId)
                            .collect(Collectors.toList());

                    // 获取该问题对应的选项
                    List<ScaleQuesOptionsDTO> optionDTOs = options.stream()
                            .filter(o -> o.getQuestion().getQuestionId().equals(q.getQuestionId()))
                            .map(o -> new ScaleQuesOptionsDTO(o.getOptionId(), o.getQuestion().getQuestionId(), o.getScore(), o.getOptionText()))
                            .collect(Collectors.toList());

                    return new ScaleQuestionsDTO(q.getQuestionId(), q.getScale().getScaleId(), q.getQuestionText(), factorIds, optionDTOs);
                })
                .collect(Collectors.toList());

        return new ScaleGetResponse(scale.getScaleId(), scale.getTitle(), scale.getManagerId(), questionDTOs);
    }

    // 通过量表id获取量表统计信息
//    public ScaleStatsDTO getScaleStats(String scaleId) {
//        // 获取量表基础信息
//        Scale scale = scaleRepository.findByIdWithDetails(scaleId)
//                .orElseThrow(() -> new RuntimeException("Scale not found"));
//
//        // 获取选项统计信息
//        List<Object[]> optionStats = testAnswersRepository.countSelectionsByScaleId(scaleId);
//        Map<String, Long> optionCountMap = optionStats.stream()
//                .collect(Collectors.toMap(
//                        arr -> (String) arr[0],
//                        arr -> (Long) arr[1]
//                ));
//
//        // 构建DTO
//        ScaleStatsDTO dto = new ScaleStatsDTO();
//        dto.setId(scale.getScaleId());
//        dto.setName(scale.getTitle());
//        dto.setManagerId(scale.getManagerId());
//
//        // 处理问题列表
//        List<QuestionStatsDTO> questionDTOs = scale.getQuestions().stream()
//                .map(question -> {
//                    QuestionStatsDTO qDto = new QuestionStatsDTO();
//                    qDto.setId(question.getQuestionId());
//                    qDto.setScaleId(scale.getScaleId());
//                    qDto.setDescription(question.getQuestionText());
//
//                    // 获取第一个因子ID
//                    if (!question.getFactors().isEmpty()) {
//                        qDto.setFactorId(question.getFactors().get(0).getFactorId());
//                    }
//
//                    // 处理选项列表
//                    List<OptionStatsDTO> optionDTOs = question.getOptions().stream()
//                            .map(option -> {
//                                OptionStatsDTO oDto = new OptionStatsDTO();
//                                oDto.setId(option.getOptionId());
//                                oDto.setQuestionId(question.getQuestionId());
//                                oDto.setScore(option.getScore());
//                                oDto.setDescription(option.getOptionText());
//
//                                // 设置统计人数
//                                oDto.setPersonCount(
//                                        optionCountMap.getOrDefault(
//                                                option.getOptionId(), 0L
//                                        ).intValue()
//                                );
//                                return oDto;
//                            })
//                            .collect(Collectors.toList());
//
//                    qDto.setOptions(optionDTOs);
//                    return qDto;
//                })
//                .collect(Collectors.toList());
//
//        dto.setQuestions(questionDTOs);
//        return dto;
//    }

    // 通过量表id获取量表统计信息 优化后
    public ScaleStatsDTO getScaleStatistics(String scaleId) {
        // 查询量表及其问题
        Scale scale = scaleRepository.findScaleWithQuestions(scaleId)
                .orElseThrow(() -> new ResourceNotFoundException("Scale not found for id: " + scaleId));

        // 获取量表的所有问题
        List<Question> questions = scale.getQuestions();

        // 逐步查询每个问题的选项及其统计信息
        List<QuestionStatsDTO> questionDTOs = new ArrayList<>();
        for (Question question : questions) {
            // 查询该问题的所有选项
            List<Option> options = optionRepository.findByQuestion_QuestionId(question.getQuestionId());

            // 统计每个选项的选择人数
            List<OptionStatsDTO> optionDTOs = new ArrayList<>();
            for (Option option : options) {
                int personCount = optionRepository.countPersonForOption(option.getOptionId());
                OptionStatsDTO optionDTO = new OptionStatsDTO(option.getOptionId(), option.getQuestion().getQuestionId(),
                        option.getScore(), option.getOptionText(), personCount);
                optionDTOs.add(optionDTO);
            }

            // 查询该问题的所有因子
            List<Factor> factors = factorRepository.findFactorsByQuestionId(question.getQuestionId());
            List<String> factorIds = factors.stream()
                    .map(Factor::getFactorId)
                    .collect(Collectors.toList());

            // 构建 QuestionDTO
            QuestionStatsDTO questionDTO = new QuestionStatsDTO(question.getQuestionId(), scale.getScaleId(),
                    question.getQuestionText(), factorIds, optionDTOs);
            questionDTOs.add(questionDTO);
        }

        // 构建最终的返回DTO
        return new ScaleStatsDTO(scale.getScaleId(), scale.getTitle(), scale.getManagerId(), questionDTOs);
    }


    @Autowired
    private ScaleCategoryRepository scaleCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    // 创建量表
    @Transactional
    public void createScale(CreateScaleRequest request) {
        // 组装 Scale 实体
        Scale scale = new Scale();
        scale.setScaleId(request.getId());
        scale.setTitle(request.getName());
        // 此处 description 可根据实际情况设置，这里置空

        // 根据 category_name 查找分类，如不存在则新建
        ScaleCategory category = scaleCategoryRepository.findByCategoryName(request.getCategory_name());
        if (category == null) {
            category = new ScaleCategory();
            // 此处简单生成一个 UUID 作为分类 id
            System.out.println(UUID.randomUUID().toString());
            category.setCategoryId("123");
            category.setCategoryName(request.getCategory_name());
            scaleCategoryRepository.save(category);
        }
        scale.setCategory(category);

        // 获取负责人信息
        Optional<User> managerOpt = userRepository.findById(request.getManagerId());
        if (!managerOpt.isPresent()) {
            throw new RuntimeException("负责人（manager）不存在");
        }
        scale.setManagerId(managerOpt.get().getUserId());

        // 处理题目及其选项
        List<Question> questionList = new ArrayList<>();
        for (QuestionRequest qRequest : request.getQuestions()) {
            Question question = new Question();
            question.setQuestionId(qRequest.getId());
            question.setQuestionText(qRequest.getDescription());
            // 设置所属量表
            question.setScale(scale);

            // 处理多个因子：遍历因子 id 列表，并查找对应的 Factor 实体
            Set<Factor> factors = new HashSet<>();
            for (String factorId : qRequest.getFactorId()) {
                Factor factor = factorRepository.findById(factorId)
                        .orElseThrow(() -> new RuntimeException("因子不存在: " + factorId));
                factors.add(factor);
            }
            question.setFactors(factors.stream().toList());

            // 处理题目选项
            List<Option> optionList = new ArrayList<>();
            for (OptionRequest oRequest : qRequest.getOptions()) {
                Option option = new Option();
                option.setOptionId(oRequest.getId());
                option.setOptionText(oRequest.getDescription());
                option.setScore(oRequest.getScore());
                // 设置所属题目
                option.setQuestion(question);
                optionList.add(option);
            }
            question.setOptions(optionList);

            questionList.add(question);
        }
        scale.setQuestions(questionList);

        // 保存量表，级联保存题目及选项
        scaleRepository.save(scale);
    }




}
