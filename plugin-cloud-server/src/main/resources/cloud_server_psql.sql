--"jdbc:postgresql://localhost:5432/HOME_AUTOMATION", "postgres", "Welcome123#"

create sequence user_id_seq start 101;
create sequence master_id_seq start 101;
create sequence slave_id_seq start 101;
create sequence sensor_id_seq start 101;
create sequence device_id_seq start 100;
create sequence sensor_history_seq start 100;
create sequence sensor_status_seq start 100;
create sequence master_history_seq start 100;
create sequence master_message_seq start 100;
create sequence fd_details_seq start 100;
create sequence exp_details_seq start 100;
create sequence exp_his_details_seq start 1;

create table usermaster (
   userid integer default nextval('user_id_seq'),
   username varchar(40) not null UNIQUE,
   useremail varchar(100) not null UNIQUE,
   userphone varchar(20) default '',
   userkey varchar(50) not null,
   userstatus varchar(1) default 'y',
   usertype varchar(1) default 'c',
   lastloggedin timestamp default current_timestamp,
   address1 varchar(150) null,
   address2 varchar(100) null,
   city varchar(50) null,
   state varchar(50) null,
   country varchar(50) null,
   pincode varchar(10) null,
   displayname varchar(100) not null,
   userlicense varchar(100) null,
   primary key(userid)
);
create table masterunitcontroller (
   masterunitid integer default nextval('master_id_seq'),
   masterunitname varchar(50) not null UNIQUE,
   masterunitmacid varchar(50) not null UNIQUE,
   masterunitipaddr varchar(20) null,
   masterunitsoftwareversion varchar(50) not null,
   masterunitlicense varchar(50) null,
   masterunitstatus varchar(1) default 'n',
   userid integer null,
   masterunitlastloggedin timestamp default current_timestamp,
   primary key(masterunitid),
   foreign key (userid) references usermaster(userid)
);
create table slaveunit (
   slaveunitid integer default nextval('slave_id_seq'),
   masterunitid integer not null UNIQUE,
   slaveunitname varchar(50) not null,
   slaveunitdisplayname  varchar(50) not null default '',
   slaveunitport varchar(50) not null default '',
   slaveswitchcnt integer not null default 0,
   slaveunittype varchar(50) not null,
   sw01 boolean not null default false,
   sw02 boolean not null default false,
   sw03 boolean not null default false,
   sw04 boolean not null default false,
   sw05 boolean not null default false,
   sw06 boolean not null default false,
   sw07 boolean not null default false,
   sw08 boolean not null default false,
   
   primary key(slaveunitid),
   foreign key (masterunitid) references masterunitcontroller(masterunitid)
);
create table devicemaster (
   deviceId integer default nextval('device_id_seq'),
   devicename varchar(30) not null,
   devicetype varchar(1) not null,
   primary key(deviceId)
);
create table sensorunit (
   sensorid integer default nextval('sensor_id_seq'),
   slaveunitid integer not null,
   sensordevice integer not null, --deviceid
   sensorname varchar(50) not null default ' ',
   sensorvalue integer not null default 0,
   sensorlastchange timestamp default current_timestamp,
   sensorswitchno integer null,
   primary key (sensorid),
   foreign key (slaveunitid) references slaveunit(slaveunitid),
   foreign key (sensordevice) references devicemaster(deviceId)
);
create table sensorstatus (
   sensorstatusid integer default nextval('sensor_status_seq'),
   sensorid integer not null UNIQUE,
   sensorontime timestamp,
   sensorofftime timestamp,
   sensorduration integer,
   primary key (sensorstatusid),
   foreign key (sensorid) references sensorunit(sensorid)
);
create table sensorhistory (
   sensorhistoryid integer default nextval('sensor_history_seq'),
   sensorid integer not null,
   sensoroldvalue varchar(3),
   sensornewvalue varchar(3),
   modifieddate timestamp,
   modifiedbyclientid varchar(50) null,
   primary key (sensorhistoryid),
   foreign key (sensorid) references sensorunit(sensorid)
);
create table masterunithistory (
  masterhistoryid integer default nextval('master_history_seq'),
  masterunitid integer not null,
  cameonline timestamp,
  wentoffline timestamp,
  duration integer,
  primary key (masterhistoryid),
  foreign key (masterunitid) references masterunitcontroller(masterunitid)
);
create table masterunitmessage (
  mastermessageid integer default nextval('master_message_seq'),
  masterunitid integer not null,
  message varchar(200) null,
  messageon timestamp,
  primary key (mastermessageid),
  foreign key (masterunitid) references masterunitcontroller(masterunitid)
);

create table finfdmaster (
  fd_id integer default nextval('fd_details_seq'),
  fd_number varchar(50) not null UNIQUE,
  fd_date timestamp,
  fd_amount integer not null,
  fd_roi varchar(5) null,
  fd_name varchar(60) not null,
  fd_userid integer not null,
  fd_maturity_amt integer not null,
  fd_maturity_date timestamp not null,
  fd_status varchar(1) default '1',
  primary key (fd_id),
  foreign key (fd_userid) references usermaster(userid)
);

create table expence_type (
  exp_id integer default nextval('exp_details_seq'),
  exp_name varchar(50) not null,
  exp_status varchar(1) default 'y',
  primary key (exp_id)
);

create table expence_history (
  exp_his_id integer default nextval('exp_his_details_seq'),
  exp_id integer not null,
  exp_date timestamp not null,
  exp_amt varchar(8) not null,
  userid integer not null,
  primary key (exp_his_id),
  foreign key (userid) references usermaster(userid),
  foreign key (exp_id) references expence_type(exp_id)
);

-- Passcode = "Welcome123#
insert into usermaster (username, userkey, userstatus, usertype, displayname, useremail) values ('admin', '1663027827', 'Y', 'A', 'IAMITN Admin', 'admin@iamitn.com');
insert into usermaster (username, userkey, userstatus, usertype, displayname, useremail) values ('suthap', '1663027827', 'Y', 'U', 'Ponraj Suthanthiramani', 'mylife.ponraj@gmail.com');
insert into usermaster (username, userkey, userstatus, usertype, displayname, useremail) values ('sujir', '1663027827', 'Y', 'U', 'Sujitra Devi Dhayalan', 'suji.vasanth@gmail.com');
insert into expence_type (exp_name) values ('HOME RENT');
insert into expence_type (exp_name) values ('POWER UNIT');
insert into expence_type (exp_name) values ('INTERNET BILL');
insert into expence_type (exp_name) values ('DIGITAL TV');
insert into expence_type (exp_name) values ('TOYS');
insert into expence_type (exp_name) values ('CIGRETTE/TEA');
insert into expence_type (exp_name) values ('DRINKS/CIGRETTE');
insert into expence_type (exp_name) values ('FOOD');
insert into expence_type (exp_name) values ('DRESS / MATERIALS');
insert into expence_type (exp_name) values ('MOBILE BILL');
insert into expence_type (exp_name) values ('CAR MAINTANENCE');
insert into expence_type (exp_name) values ('FUEL BILL');
insert into expence_type (exp_name) values ('LAND TAX');
insert into expence_type (exp_name) values ('DONATIONS');
insert into expence_type (exp_name) values ('DEPOSITS');
insert into expence_type (exp_name) values ('PARENTS EXPENCE');
insert into expence_type (exp_name) values ('TOLL EXPENCES');
insert into expence_type (exp_name) values ('TRAVEL EXPENCES');

insert into devicemaster (devicename, devicetype) values ('No Device','N');
insert into devicemaster (devicename, devicetype) values ('Light1','O');
insert into devicemaster (devicename, devicetype) values ('Light2','O');
insert into devicemaster (devicename, devicetype) values ('Light3','O');
insert into devicemaster (devicename, devicetype) values ('Light4','O');
insert into devicemaster (devicename, devicetype) values ('Fan1','O');
insert into devicemaster (devicename, devicetype) values ('Fan2','O');
insert into devicemaster (devicename, devicetype) values ('Fan3','O');
insert into devicemaster (devicename, devicetype) values ('Fan4','O');
insert into devicemaster (devicename, devicetype) values ('Plug Point1','O');
insert into devicemaster (devicename, devicetype) values ('Plug Point2','O');
insert into devicemaster (devicename, devicetype) values ('Night Light1','O');
insert into devicemaster (devicename, devicetype) values ('Night Light2','O');
insert into devicemaster (devicename, devicetype) values ('Television','O');
insert into devicemaster (devicename, devicetype) values ('Audio Player','O');
insert into devicemaster (devicename, devicetype) values ('DVD Player','O');
insert into devicemaster (devicename, devicetype) values ('Desktop1','O');
insert into devicemaster (devicename, devicetype) values ('Desktop2','O');

insert into devicemaster (devicename, devicetype) values ('TempSensor','I');
insert into devicemaster (devicename, devicetype) values ('LightSensor','I');
insert into devicemaster (devicename, devicetype) values ('PIR','I');
insert into devicemaster (devicename, devicetype) values ('Touch','I');

commit;
select * from usermaster;
select * from devicemaster;
select * from expence_type;

delete from usermaster;

drop table expence_history;
drop table expence_type;
drop table masterunitmessage;
drop table masterunithistory;
drop table sensorhistory;
drop table sensorstatus;
drop table sensorunit;
drop table devicemaster;
drop table slaveunit;
drop table masterunitcontroller;
drop table finfdmaster;
drop table usermaster;

drop sequence exp_his_details_seq;
drop sequence exp_details_seq;
drop sequence fd_details_seq;
drop sequence master_message_seq;
drop sequence master_history_seq;
drop sequence sensor_status_seq;
drop sequence sensor_history_seq;
drop sequence sensor_id_seq;
drop sequence device_id_seq;
drop sequence slave_id_seq;
drop sequence master_id_seq;
drop sequence user_id_seq;