package com.example.mind.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SubmitAnswerRequest  {
    @JsonProperty("userid")
    private String userid;  // 提交者ID
    @JsonProperty("pid")
    private String pid;     // 测试任务ID
    @JsonProperty("answer")
    private List<String> answer;  // 答案数组，每个字符串格式为 "量表id-问题id-选项id"

    // getters 和 setters
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public List<String> getAnswer() {
        return answer;
    }
    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
}
