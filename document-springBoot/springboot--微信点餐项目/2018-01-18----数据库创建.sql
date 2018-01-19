#微信点餐数据库
#给表添加注释的范式
#1、 alter table product_category comment "商品类目标";
#2、 直接在结尾处添加comment

-- 类目表
create table product_category(
	category_id int not null auto_increment,
	category_name varchar(64) not null comment '类目名字',
	category_type int not null comment '类目编号',
	create_time timestamp not null default current_timestamp comment '创建时间',
	update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key(category_id)
)comment "类目标";
#alter table product_category comment "类目表";

-- 商品表
 create table product_info(
 	product_id varchar(32) not null ,
 	product_name varchar(64) not null comment '商品名称',
 	product_price decimal(8,2) not null comment '单价',
 	product_stock	int  not null comment '库存',
 	product_description varchar(64) comment '描述',
 	product_icon varchar(512) comment '小图',
 	product_status tinyint(3) Default 0 comment '商品状态 0正常1下架',
 	category_type int not null comment '类目编号',
 	create_time timestamp not null default current_timestamp comment '创建时间',
 	update_time timestamp not null default current_timestamp on update current_timestamp comment '修改时间'	,
 	primary key(product_id)
 )comment "商品表";
 #alter table product_info comment "商品表";

 -- 订单表主表
 create table order_master(
 	order_id varchar(32) not null,
 	buyer_name varchar(32) not null comment '买家姓名',
 	buyer_phone varchar(32) not null comment '买家电话',
 	buyer_address varchar(128) not null comment '买家地址',
 	buyer_openid varchar(64) not null comment '买家微信openid',
 	order_amount decimal(8,2) not null comment '订单总金额',
 	order_status tinyint(3) not null default 0 comment '订单状态，默认新下单',
 	pay_status tinyint(3) not null default 0 comment '制服状态，默认未支付',
 	cteate_time timestamp not null default current_timestamp comment '创建时间',
 	update_time timestamp not null default current_timestamp on update current_timestamp  comment '修改时间',
 	primary key(order_id),
 	key idx_buyer_openid(buyer_openid)
 )comment "订单表主表";
#alter table order_master comment "订单表主表";


 -- 订单详情表
create table order_detail (
	detail_id varchar(32) not null ,
	order_id varchar(32) not null ,
	product_id varchar(32) not null,
	product_name varchar(64) not null comment '商品名称',
	product_price decimal(8,2) not null comment '当前价格，单位分',
	product_quantity int not null comment '数量',
	product_icon varchar(512) comment '小图',
	create_time timestamp not null default current_timestamp comment '创建时间',
	update_time timestamp not null default current_timestamp on update current_timestamp comment "修改时间",
	primary key(detail_id),
	key idx_order_id(order_id)
)comment "订单详情表";
#alter table order_detail comment "订单详情表";

-- 卖家信息表(登录后台使用，卖家登录之后可以直接采用微信扫码登录，不使用账号密码)
create table seller_info(
	id varchar(32) not null,
	username varchar(32) not null ,
	password varchar(32) not null,
	openid varchar(64) not null comment "微信openid",
	cteat_time timestamp not null default current_timestamp comment "创建时间",
	update_time timestamp not  null default current_timestamp on update current_timestamp comment "修改时间",
	primary key(id) 
) comment "卖家信息表(登录后台使用，卖家登录之后可以直接采用微信扫码登录，不使用账号密码)";
#alter table seller_info comment "卖家信息表(登录后台使用，卖家登录之后可以直接采用微信扫码登录，不使用账号密码)";

