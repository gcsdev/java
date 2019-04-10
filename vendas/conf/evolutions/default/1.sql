# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table detail (
  id                            bigint auto_increment not null,
  register                      integer,
  establishment                 integer,
  initial_date_transaction      varchar(255),
  sale_date                     varchar(255),
  event_hour                    integer,
  event_type                    integer,
  transaction_type              integer,
  serie_number                  varchar(255),
  transaction_code              varchar(255),
  order_code                    varchar(255),
  transaction_value             integer,
  parcel_value                  integer,
  payment                       varchar(255),
  plan                          varchar(255),
  parcel                        varchar(255),
  parcel_amount                 integer,
  payment_data_prevision        varchar(255),
  taxa_parcelamento_comprador   integer,
  ticket_rate                   integer,
  transaction_original_value    integer,
  salesman_parcel_rate          integer,
  intermediates_rate            integer,
  intermediation                integer,
  salesman_ticket_rate          integer,
  application_pass              integer,
  transaction_liquid_value      integer,
  payment_status                integer,
  filler0                       varchar(255),
  payment_typer                 integer,
  financial_instituition        varchar(255),
  input_channel                 varchar(255),
  reader                        integer,
  capture                       integer,
  logic_number                  varchar(255),
  nsu                           varchar(255),
  filler1                       varchar(255),
  bin_card                      varchar(255),
  holder_card                   varchar(255),
  auth_code                     varchar(255),
  cv_code                       varchar(255),
  reserved                      integer,
  header_id                     bigint,
  trailler_id                   bigint,
  constraint pk_detail primary key (id)
);

create table header (
  id                            bigint auto_increment not null,
  register                      integer,
  establishment                 integer,
  date_processing               varchar(255),
  initial_period                integer,
  final_period                  integer,
  sequence                      integer,
  enterprise                    varchar(255),
  extract_type                  integer,
  filler                        varchar(255),
  layout_version                varchar(255),
  release_version               varchar(255),
  reserved                      varchar(255),
  constraint pk_header primary key (id)
);

create table trailler (
  id                            bigint auto_increment not null,
  register_type                 integer,
  register_total                integer,
  reserved                      varchar(255),
  constraint pk_trailler primary key (id)
);

create index ix_detail_header_id on detail (header_id);
alter table detail add constraint fk_detail_header_id foreign key (header_id) references header (id) on delete restrict on update restrict;

create index ix_detail_trailler_id on detail (trailler_id);
alter table detail add constraint fk_detail_trailler_id foreign key (trailler_id) references trailler (id) on delete restrict on update restrict;


# --- !Downs

alter table detail drop foreign key fk_detail_header_id;
drop index ix_detail_header_id on detail;

alter table detail drop foreign key fk_detail_trailler_id;
drop index ix_detail_trailler_id on detail;

drop table if exists detail;

drop table if exists header;

drop table if exists trailler;

