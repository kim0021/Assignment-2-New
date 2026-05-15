public class Student {
    String id, name, major;           //학번, 이름, 학과
    int year;
    Course[] courses = new Course[7]; // 최대 7과목 배열
    int courseCount = 0;              // 현재 입력된 과목 수
    int totalCredits = 0;             // 학점 합계
    double averageGPA = 0.0;          // 평균학점
    int rank;                         // 석차

    public Student(String id, String name, int year, String major) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.major = major;
    }

    // 과목 배열에 추가
    public void addCourse(Course c) {
        if (courseCount < 7) {
            courses[courseCount++] = c;
            calculateGPA(); // 과목 추가 시 실시간 평균 업데이트
        }
    }

    // 개인 평균 평점 계산
    private void calculateGPA() {
        double sum = 0;
        int credits = 0;
        for (int i = 0; i < courseCount; i++) {
            sum += (courses[i].score * courses[i].credit);
            credits += courses[i].credit;
        }
        this.totalCredits = credits;
        this.averageGPA = (credits == 0) ? 0 : sum / credits;
    }
}