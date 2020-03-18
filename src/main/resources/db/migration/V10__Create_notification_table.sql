create table notification
(
	id BIGINT auto_increment,
	notifier BIGINT not null,
	receiver BIGINT not null,
	outerId BIGINT not null,
	type INT NOT NULL,
	gmt_create BIGINT,
	status INT default 0 not null,
	constraint notification_pk
		primary key (id)
);

comment on column notification.notifier is '发送通知的人';

comment on column notification.receiver is '接收通知的人';

comment on column notification.outerId is '问题ID或者回复ID';

comment on column notification.type is '判断是评论还是回复';

comment on column notification.gmt_create is '创建时间';

comment on column notification.status is '判断已读或未读状态';

