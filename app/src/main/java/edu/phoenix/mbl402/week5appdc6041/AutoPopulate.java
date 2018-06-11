package edu.phoenix.mbl402.week5appdc6041;

public class AutoPopulate {

    public AutoPopulate(DatabaseHelper db){

        SsgContainer<Student> studentList = new SsgContainer(Student.class);
        Student a = new Student(1,"Sally","Smith");
        studentList.addObject(a);
        Student b = new Student(2,"Mark","Front");
        studentList.addObject(b);
        Student c = new Student(3,"Rick","Heart");
        studentList.addObject(c);
        Student d = new Student(4,"Justin","Jones");
        studentList.addObject(d);

        for (int i = 0; i < studentList.getSsgList().size(); i++){
            if(db.dataCheck(DatabaseHelper.STUDENT_TABLE_NAME,
                    DatabaseHelper.STUDENT_COL_1,i+1)){
                db.updateRecord(studentList.getObject(i).getContentValues(),DatabaseHelper.STUDENT_TABLE_NAME,
                        DatabaseHelper.STUDENT_COL_1);
            } else {
                db.insertData(studentList.getObject(i).getContentValues(),DatabaseHelper.STUDENT_TABLE_NAME);
            }
        }

        SsgContainer<Subjects> classList = new SsgContainer(Subjects.class);
        Subjects e = new Subjects(100,"Biology");
        classList.addObject(e);
        Subjects f = new Subjects(110,"Gym");
        classList.addObject(f);
        Subjects g = new Subjects(120,"English");
        classList.addObject(g);
        Subjects h = new Subjects(130,"Science");
        classList.addObject(h);
        Subjects j = new Subjects(140,"Computers");
        classList.addObject(j);

        for (int i = 0; i < classList.getSsgList().size(); i++){
            if(db.dataCheck(DatabaseHelper.CLASSES_TABLE_NAME,
                    DatabaseHelper.CLASSES_COL_1,classList.getObject(i).getClassID())){
                db.updateRecord(classList.getObject(i).getContentValues(),DatabaseHelper.CLASSES_TABLE_NAME,
                        DatabaseHelper.CLASSES_COL_1);
            } else {
                db.insertData(classList.getObject(i).getContentValues(),DatabaseHelper.CLASSES_TABLE_NAME);
            }
        }

        SsgContainer<Grades> gradeList = new SsgContainer(Grades.class);
        Grades k = new Grades(1,1,100,70.00);
        gradeList.addObject(k);
        Grades l = new Grades(2,1,110,90.00);
        gradeList.addObject(l);
        Grades m = new Grades(3,2,120,40.00);
        gradeList.addObject(m);
        Grades n = new Grades(4,2,110,10.00);
        gradeList.addObject(n);
        Grades o = new Grades(5,3,130,85.00);
        gradeList.addObject(o);
        Grades p = new Grades(6,3,100,90.00);
        gradeList.addObject(p);
        Grades q = new Grades(7,3,110,45.00);
        gradeList.addObject(q);
        Grades r = new Grades(8,4,140,45.00);
        gradeList.addObject(r);
        Grades s = new Grades(9,4,120,70.00);
        gradeList.addObject(s);

        for (int i = 0; i < gradeList.getSsgList().size(); i++){
            if(db.dataCheck(DatabaseHelper.GRADES_TABLE_NAME,
                    DatabaseHelper.GRADES_COL_1,gradeList.getObject(i).getGradeID())){
                db.updateRecord(gradeList.getObject(i).getContentValues(),DatabaseHelper.GRADES_TABLE_NAME,
                        DatabaseHelper.GRADES_COL_1);
            } else {
                db.insertData(gradeList.getObject(i).getContentValues(),DatabaseHelper.GRADES_TABLE_NAME);
            }
        }
    }
}
