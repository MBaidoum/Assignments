package com.company;

public class StudentMarks {

    public abstract static class Marks{

        public abstract double getPercentage();
    }

    public static class StudentA extends Marks {

        private double grade1;
        private double grade2;
        private double grade3;

        public StudentA(double grade1, double grade2, double grade3) {
            this.grade1 = grade1;
            this.grade2 = grade2;
            this.grade3 = grade3;
        }

        @Override
        public double getPercentage() {
            return (grade1 + grade2 + grade3)/300;
        }

    }

    public static class StudentB extends Marks {

        private double grade1;
        private double grade2;
        private double grade3;
        private double grade4;

        public StudentB(double grade1, double grade2, double grade3, double grade4) {
            this.grade1 = grade1;
            this.grade2 = grade2;
            this.grade3 = grade3;
            this.grade4 = grade4;
        }

        @Override
        public double getPercentage() {
            return (grade1 + grade2 + grade3 + grade4)/400;
        }

    }

    public static void main(String[] args) {

        StudentA student1 = new StudentA(88, 46, 97);
        StudentB student2 = new StudentB(94, 85, 92, 100);
        double percent1 = student1.getPercentage();
        double percent2 = student2.getPercentage();
        System.out.println("Student A has a percentage of " + percent1 * 100 + "%");
        System.out.println("Student B has a percentage of " + percent2 * 100 + "%");
    }
}
