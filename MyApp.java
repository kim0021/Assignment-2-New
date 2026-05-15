import java.util.Scanner;
import java.util.InputMismatchException;

public class MyApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GradeProcessor gp = new GradeProcessor();

        System.out.print("처리할 학생 수 입력: ");
        int n = sc.nextInt();
        Student[] studentArray = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n[" + (i + 1) + "번째 학생 정보 입력]");
            System.out.print("학번 이름 학년 학과(공백구분): ");
            Student s = new Student(sc.next(), sc.next(), sc.nextInt(), sc.next());

            for (int j = 0; j < 7; j++) {
                int choice = -1; // 사용자의 선택을 저장할 변수

                try {
                    System.out.print("새 과목을 입력하시겠습니까? (계속 1, 종료 0): ");
                    choice = sc.nextInt(); // 여기서 문자가 들어오면 catch로 이동
                } 
                catch (InputMismatchException e) {
                    // 잘못 입력된 문자열을 읽어서 제거함
                    sc.next(); 
                    System.out.println(">> 경고: 숫자가 아닙니다. 1 또는 0을 입력하세요!");
                    j--; // 횟수를 차감하여 다시 현재 순서(j)를 진행하게 함
                    continue; // 루프의 처음(조건식)으로 돌아감
                }
                //예외 처리 끝

                if (choice == 0) { 
                    break;
                }

                // 데이터 입력 (정상적인 숫자가 입력되었을 때만 실행됨)
                try {
                    System.out.print("과목명: ");
                    String cName = sc.next();
                    System.out.print("학점 이수구분 평점(예: 3 전공 4.5): ");
                    int credit = sc.nextInt();
                    String type = sc.next();
                    double score = sc.nextDouble();

                    s.addCourse(new Course(cName, credit, type, score));
                } catch (InputMismatchException e) {
                    sc.next(); // 잘못된 입력 제거
                    System.out.println(">> 데이터 입력 오류! 해당 과목 입력을 취소하고 다시 시도합니다.");
                    j--; 
                    continue;
                }
            }
            studentArray[i] = s;
        }

        // 데이터 처리
        gp.sortStudents(studentArray, n);
        gp.assignRanks(studentArray, n);
        double mean = gp.getOverallMean(studentArray, n);
        double stdDev = gp.getStandardDeviation(studentArray, n, mean);

        // 결과 출력
        System.out.println("\n" + "=".repeat(50));
        System.out.println("성적 처리 최종 결과 리포트");
        System.out.println("=".repeat(50));

        for (int i = 0; i < n; i++) {
            Student s = studentArray[i];
            System.out.printf("[%d등] %s (%s) | %d학년 %s\n", s.rank, s.name, s.id, s.year, s.major);
            System.out.println("   수강 과목 상세:");
            for (int j = 0; j < s.courseCount; j++) {
                Course c = s.courses[j];
                System.out.printf("   - %s: %d학점 [%s] -> %s(%.1f)\n", c.name, c.credit, c.type, c.grade, c.score);
            }
            System.out.printf("   ▶ 총 신청학점: %d | 평균 평점: %.2f\n\n", s.totalCredits, s.averageGPA);
        }

        System.out.println("-".repeat(50));
        System.out.printf("전체 학생 평균: %.2f | 표준편차: %.2f\n", mean, stdDev);
        System.out.println("-".repeat(50));
    }
}