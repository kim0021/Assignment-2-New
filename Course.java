public class Course {
    String name;      // 과목명
    int credit;       // 학점
    String type;      // 이수구분
    double score;     // 평점 (0.0 ~ 4.5)
    String grade;     // 등급 (A+ ~ F)

    public Course(String name, int credit, String type, double score) {
        this.name = name;
        this.credit = credit;
        this.type = type;
        this.score = score;
        this.grade = convertToGrade(score);
    }

    // 평점에 따른 등급 변환 로직
    private String convertToGrade(double score) {
        if (score >= 4.5) return "A+";
        if (score >= 4.0) return "A0";
        if (score >= 3.5) return "B+";
        if (score >= 3.0) return "B0";
        if (score >= 2.5) return "C+";
        if (score >= 2.0) return "C0";
        return "F";
    }
}