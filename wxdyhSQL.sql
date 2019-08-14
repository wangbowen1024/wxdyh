create database wxdyh;
use wxdyh;

-- 请求文本记录
create table textRecord(
	id int primary key auto_increment,
	toUserName text,
    fromUserName text,
    createTime text,
    msgType text,
    msgId text,
    content text
);

-- 请求图片记录
create table imageRecord(
	id int primary key auto_increment,
	toUserName text,
    fromUserName text,
    createTime text,
    msgType text,
    msgId text,
    picUrl text,
    mediaId text
);

-- 请求语音记录
create table voiceRecord(
	id int primary key auto_increment,
	toUserName text,
    fromUserName text,
    createTime text,
    msgType text,
    msgId text,
    mediaId text,
    format text,
    recognition text
);

-- 请求视频记录
create table videoRecord(
	id int primary key auto_increment,
	toUserName text,
    fromUserName text,
    createTime text,
    msgType text,
    msgId text,
    mediaId text,
    thumbMediaId text
);

-- 规则列表
create table rule(
	id int primary key auto_increment,
	name text,
    type text,
    content text,
    replayType text,
    replayContent text
);

-- 用户表
create table user(
	id int primary key auto_increment,
    openId varchar(50) unique,
    status int default 1,
    authority text
);

-- 关注信息回复表
create table subscribeMessage(
	id int primary key default 1,
	content text 
);
insert into subscribeMessage(content)values('o(*￣▽￣*)ブ欢迎关注公众号！');