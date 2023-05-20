DROP TABLE IF EXISTS lesson;
CREATE TABLE lesson(
    `lesson_id` VARCHAR(255) NOT NULL   COMMENT '课程ID' ,
    `lesson_name` VARCHAR(90) NOT NULL   COMMENT '课程名' ,
    `lesson_picture` VARCHAR(255) NOT NULL   COMMENT '课程图片' ,
    `intro` VARCHAR(900) NOT NULL   COMMENT '课程说明' ,
    `lesson_mark` DECIMAL(24,6)    COMMENT '课程评分' ,
    `category` VARCHAR(32) NOT NULL   COMMENT '课程类别' ,
    `knowledge_id` VARCHAR(255) NOT NULL   COMMENT '知识点ID' ,
    PRIMARY KEY (lesson_id)
)  COMMENT = '课程';

DROP TABLE IF EXISTS teacher_lesson;
CREATE TABLE teacher_lesson(
    `teacher_id` VARCHAR(255) NOT NULL   COMMENT '教师ID' ,
    `class_id` VARCHAR(255) NOT NULL   COMMENT '课程ID' ,
    PRIMARY KEY (teacher_id,class_id)
)  COMMENT = '老师课堂';

DROP TABLE IF EXISTS lesson_student;
CREATE TABLE lesson_student(
    `class_id` VARCHAR(255) NOT NULL   COMMENT '课程ID' ,
    `student_id` VARCHAR(255) NOT NULL   COMMENT '学生ID' ,
    PRIMARY KEY (class_id,student_id)
)  COMMENT = '课堂学生';

DROP TABLE IF EXISTS announcment;
CREATE TABLE announcment(
    `announcment_id` VARCHAR(255) NOT NULL   COMMENT '公告ID' ,
    `title` VARCHAR(255) NOT NULL   COMMENT '标题' ,
    `created_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    `announcment_content` VARCHAR(900) NOT NULL   COMMENT '公告内容' ,
    PRIMARY KEY (announcment_id)
)  COMMENT = '公告';

DROP TABLE IF EXISTS lesson_announcment;
CREATE TABLE lesson_announcment(
    `class_id` VARCHAR(255) NOT NULL   COMMENT '课程ID' ,
    `announcment_id` VARCHAR(255) NOT NULL   COMMENT '公告ID' ,
    PRIMARY KEY (class_id,announcment_id)
)  COMMENT = '课堂公告';

DROP TABLE IF EXISTS homework;
CREATE TABLE homework(
    `homework_id` VARCHAR(255) NOT NULL   COMMENT '作业ID' ,
    `title` VARCHAR(255) NOT NULL   COMMENT '标题' ,
    `homework_content` VARCHAR(900) NOT NULL   COMMENT '作业内容' ,
    `created_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    `deadline` DATETIME    COMMENT '截止时间' ,
    PRIMARY KEY (homework_id)
)  COMMENT = '作业';

DROP TABLE IF EXISTS lesson_homework;
CREATE TABLE lesson_homework(
    `class_id` VARCHAR(255) NOT NULL   COMMENT '课程ID' ,
    `homework_id` VARCHAR(255) NOT NULL   COMMENT '作业ID' ,
    PRIMARY KEY (class_id,homework_id)
)  COMMENT = '课堂作业';

DROP TABLE IF EXISTS teaching_material;
CREATE TABLE teaching_material(
    `material_id` VARCHAR(255) NOT NULL   COMMENT '资源ID' ,
    `title` VARCHAR(255) NOT NULL   COMMENT '标题' ,
    `material_link` VARCHAR(255) NOT NULL   COMMENT '资源链接' ,
    `ceated_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    PRIMARY KEY (material_id)
)  COMMENT = '教学资料';

DROP TABLE IF EXISTS lesson_resource;
CREATE TABLE lesson_resource(
    `class_id` VARCHAR(255) NOT NULL   COMMENT '课程ID' ,
    `material_id` VARCHAR(255) NOT NULL   COMMENT '资源ID' ,
    PRIMARY KEY (class_id,material_id)
)  COMMENT = '课堂资源';

DROP TABLE IF EXISTS lesson_stuent_graph;
CREATE TABLE lesson_stuent_graph(
    `class_id` VARCHAR(255) NOT NULL   COMMENT '类别ID' ,
    `student_id` VARCHAR(255) NOT NULL   COMMENT '学生ID' ,
    `graph_id` VARCHAR(255) NOT NULL   COMMENT '图谱ID' ,
    PRIMARY KEY (class_id,student_id,graph_id)
)  COMMENT = '课堂学生图谱';

DROP TABLE IF EXISTS classes;
CREATE TABLE classes(
    `class_id` VARCHAR(255) NOT NULL   COMMENT '课堂ID' ,
    `teacher_id` VARCHAR(255) NOT NULL   COMMENT '教师ID' ,
    `created_time` DATETIME NOT NULL   COMMENT '创建时间' ,
    `class_name` VARCHAR(255) NOT NULL   COMMENT '课堂名称' ,
    `class_picture` VARCHAR(255) NOT NULL   COMMENT '课堂图片' ,
    PRIMARY KEY (class_id)
)  COMMENT = '课堂';

