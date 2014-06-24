/*==============================================================*/
/* Table: UserTypes                                             */
/*==============================================================*/
insert into UserTypes (usertype) values
    ('Administrator'),
    ('Client');

/*==============================================================*/
/* Table: Users                                                 */
/*==============================================================*/
insert into Users (usertype_id, login, password) values
    ('1', 'root',  'root'),
    ('2', 'user', 'user'),
    ('2', 'petrov', 'petrovpass'),
    ('2', 'smith',  'smithpass');

/*==============================================================*/
/* Table: Vehicles                                              */
/*==============================================================*/
insert into Vehicles (make, model, auto_gearbox,
                      air_conditioner, seats, daily_price) values
    ('Audi',     'A4',   true,  true,  4, 50.00),
    ('Audi',     'A6',   true,  true,  5, 70.00),
    ('Mercedes', 'Vito', false, true , 7, 65.50),
    ('VW',       'Golf', true,  true,  4, 39.99),
    ('VW',       'Polo', false, false, 4, 29.99);

/*==============================================================*/
/* Table: Passports                                             */
/*==============================================================*/
insert into Passports (last_name, first_name, patronymic, birthday,
                       p_series, p_number, who_issued, when_issued) values
    (
    'Иванов',
    'Иван',
    'Иванович',
    '1983-10-03',
    'АВ',
    '954326',
    'Святошинським РУ ГУ МВС України у м. Києві',
    '2001-06-05'
    ),
    (
    'Петров',
    'Петр',
    'Петрович',
    '1976-02-07',
    'ВН',
    '658715',
    'Шевченківським РУ ГУ МВС України у м. Києві',
    '1994-03-19'
    ),
    (
    'Смит',
    'Джон',
    null,
    '1978-11-21',
    'СР',
    '698541',
    'Дарницьким РУ ГУ МВС України у м. Києві',
    '1996-08-26'
    );

/*==============================================================*/
/* Table: Orders                                                */
/*==============================================================*/
insert into Orders
(
    vehicle_id,
    user_id,
    passport_id,
    pick_up_date,
    drop_off_date,
    rent_cost,
    processed,
    rejected,
    reject_desc,
    picked,
    returned,
    damaged,
    damage_desc,
    damage_cost,
    paid
)
values
(
    '1',
    '2',
    '1',
    '2014-07-10 15:00:00',
    '2014-07-20 11:00:00',
    500.00,
    false,
    false,
    null,
    false,
    false,
    false,
    null,
    null,
    false
),
(
    '1',
    '3',
    '2',
    '2014-06-25 10:30:00',
    '2014-06-30 12:00:00',
    250.00,
    false,
    false,
    null,
    false,
    false,
    false,
    null,
    null,
    false
),
(
    '4',
    '4',
    '3',
    '2014-05-10 23:00:00',
    '2014-05-12 16:45:00',
    79.98,
    false,
    false,
    null,
    false,
    false,
    false,
    null,
    null,
    false
);