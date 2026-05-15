public class GradeProcessor {
    // GPA 기준 내림차순 정렬 (버블 정렬 사용)
    public void sortStudents(Student[] students, int count) {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (students[j].averageGPA < students[j + 1].averageGPA) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    // 정렬된 상태에서 석차 부여
    public void assignRanks(Student[] students, int count) {
        for (int i = 0; i < count; i++) {
            students[i].rank = i + 1;
        }
    }

    // 전체 평균 계산
    public double getOverallMean(Student[] students, int count) {
        double sum = 0;
        for (int i = 0; i < count; i++) sum += students[i].averageGPA;
        return (count == 0) ? 0 : sum / count;
    }

    // 표준편차 계산
    public double getStandardDeviation(Student[] students, int count, double mean) {
        if (count == 0) return 0;
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += Math.pow(students[i].averageGPA - mean, 2);
        }
        return Math.sqrt(sum / count);
    }
}