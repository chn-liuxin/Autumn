drop table if exists lesson;

drop table if exists record;

drop table if exists review_exp;

drop table if exists review_theory;

drop table if exists subject;

drop table if exists teacher;

drop table if exists user;

drop table if exists class;

/*==============================================================*/
/* Table: class                                                 */
/*==============================================================*/
create table class
(
   class_id             varchar(10) not null,
   class_session        smallint not null,
   class_major          varchar(15) not null,
   class_number         tinyint not null,
   primary key (class_id)
);

/*==============================================================*/
/* Table: lesson                                                */
/*==============================================================*/
create table lesson
(
   lesson_id            varchar(10) not null,
   subject_id           varchar(10) not null,
   teacher_id           varchar(10) not null,
   class_id             varchar(10) not null,
   subject_start_time   tinyint not null,
   subject_end_time     tinyint not null,
   lesson_day           tinyint not null,
   lesson_time          varchar(10) not null,
   lesson_room          varchar(10) not null,
   primary key (lesson_id)
);

/*==============================================================*/
/* Table: record                                                */
/*==============================================================*/
create table record
(
   record_id            varchar(10) not null,
   type                 tinyint not null,
   teacher_id           varchar(10) not null,
   review_teacher_id    varchar(10) not null,
   lesson_id            varchar(10) not null,
   status               tinyint not null,
   is_complete          boolean not null,
   primary key (record_id)
);

/*==============================================================*/
/* Table: review_exp                                            */
/*==============================================================*/
create table review_exp
(
   review_id            varchar(10) not null,
   teacher_id           varchar(10) not null,
   review_teacher_id    varchar(10) not null,
   class_id             varchar(10) not null,
   subject_id           varchar(10) not null,
   lesson_id            varchar(10) not null,
   theme                varchar(20) not null,
   review_date          date not null,
   review_time          varchar(3) not null,
   score1               float,
   score2               float,
   score3               float not null,
   score4               float not null,
   score5               float not null,
   score6               float not null,
   score7               float not null,
   score8               float not null,
   total_score          float not null,
   comment              varchar(500) not null,
   management           tinyint not null,
   type                 tinyint not null,
   primary key (review_id)
);

/*==============================================================*/
/* Table: review_theory                                         */
/*==============================================================*/
create table review_theory
(
   review_id            varchar(10) not null,
   teacher_id           varchar(10) not null,
   review_teacher_id    varchar(10) not null,
   class_id             varchar(10) not null,
   subject_id           varchar(10) not null,
   lesson_id            varchar(10) not null,
   theme                varchar(20) not null,
   review_date          date not null,
   review_time          varchar(3) not null,
   score1               float not null,
   score2               float not null,
   score3               float not null,
   score4               float not null,
   score5               float not null,
   score6               float not null,
   score7               float not null,
   score8               float not null,
   score9               float not null,
   score10              float not null,
   total_score          float not null,
   teacher_situation    tinyint not null,
   atmosphere           tinyint not null,
   student_situation    tinyint not null,
   book_situation       tinyint not null,
   head_rate            tinyint not null,
   sit_situation        tinyint not null,
   teach_type           tinyint not null,
   overall_evaluation   varchar(500) not null,
   recommend            varchar(500) not null,
   primary key (review_id)
);

/*==============================================================*/
/* Table: subject                                               */
/*==============================================================*/
create table subject
(
   subject_id           varchar(10) not null,
   subject_name         varchar(20) not null,
   subject_credit       float not null,
   subject_hour         tinyint not null,
   subject_type         varchar(5) not null,
   subject_start_time   tinyint not null,
   subject_end_time     tinyint not null,
   primary key (subject_id)
);

/*==============================================================*/
/* Table: teacher                                               */
/*==============================================================*/
create table teacher
(
   teacher_id           varchar(10) not null,
   user_id              varchar(10) not null,
   teacher_name         varchar(10) not null,
   teacher_sex          char(2) not null,
   teacher_birthday     date,
   teacher_position     varchar(10) not null,
   teacher_phone        varchar(15),
   primary key (teacher_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              varchar(10) not null,
   user_identity        smallint not null,
   user_name            varchar(15) not null,
   user_password        varchar(15) not null,
   primary key (user_id)
);

alter table lesson add constraint FK_Reference_2 foreign key (class_id)
      references class (class_id) on delete restrict on update restrict;

alter table lesson add constraint FK_Reference_3 foreign key (subject_id)
      references subject (subject_id) on delete restrict on update restrict;

alter table lesson add constraint FK_Reference_4 foreign key (teacher_id)
      references teacher (teacher_id) on delete restrict on update restrict;

alter table record add constraint FK_Reference_5 foreign key (teacher_id)
      references teacher (teacher_id) on delete restrict on update restrict;

alter table review_exp add constraint FK_Reference_6 foreign key (teacher_id)
      references teacher (teacher_id) on delete restrict on update restrict;

alter table review_exp add constraint FK_Reference_7 foreign key (subject_id)
      references subject (subject_id) on delete restrict on update restrict;

alter table review_exp add constraint FK_Reference_8 foreign key (class_id)
      references class (class_id) on delete restrict on update restrict;

alter table review_exp add constraint FK_Reference_9 foreign key (lesson_id)
      references lesson (lesson_id) on delete restrict on update restrict;

alter table review_theory add constraint FK_Reference_10 foreign key (lesson_id)
      references lesson (lesson_id) on delete restrict on update restrict;

alter table review_theory add constraint FK_Reference_11 foreign key (teacher_id)
      references teacher (teacher_id) on delete restrict on update restrict;

alter table review_theory add constraint FK_Reference_12 foreign key (class_id)
      references class (class_id) on delete restrict on update restrict;

alter table review_theory add constraint FK_Reference_13 foreign key (subject_id)
      references subject (subject_id) on delete restrict on update restrict;

alter table teacher add constraint FK_Reference_1 foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;
