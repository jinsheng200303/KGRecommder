package cn.hunnu.recommender.examination.entity;

import lombok.Data;

@Data
public class Exams {
    private String examId;
    private String examTitle;

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }
}
