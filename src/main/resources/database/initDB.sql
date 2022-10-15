-- create table user_types
-- (
--     user_types_id    int identity primary key,
--     user_types_title nvarchar(50)
-- );

create table if not exists user_posts
(
    user_post_id bigserial primary key,
    user_post_title varchar(250) not null
);

-- create table if not exists users
-- (
--     id            bigserial primary key,
--     username      varchar(250) not null,
--     password      varchar(250) not null,
--     user_types_id bigint references user_ (user_types_id),
--     user_post_id  bigint references user_posts (user_post_id)
-- );

create table if not exists publishers
(
    publisher_id bigserial
    primary
    key,
    publisher_title
    varchar
(
    250
) not null
    );

create table if not exists genres
(
    genre_id
    bigserial
    primary
    key,
    genre_name
    varchar
(
    250
) not null
    );

create table if not exists authors
(
    author_id
    bigserial
    primary
    key,
    author_name
    varchar
(
    250
) not null
    );

create table if not exists books
(
    book_id
    bigserial
    primary
    key,
    title
    varchar
(
    250
) not null,
    author_id bigint references authors
(
    author_id
),
    publisher_id bigint references publishers
(
    publisher_id
),
    genre_id bigint references genres
(
    genre_id
),
    isbn varchar
(
    250
) not null
    );

create table if not exists cities
(
    city_id
    bigserial
    primary
    key,
    title
    varchar
(
    250
) not null
    );

create table if not exists clients
(
    client_id
    bigserial
    primary
    key,
    client_name
    varchar
(
    250
) not null ,
    email varchar
(
    250
) not null ,
    phone varchar
(
    250
) not null ,
    client_address varchar
(
    250
) not null ,
    city_id bigint references cities
(
    city_id
)
    );

create table if not exists sales
(
    sale_id
    bigserial
    primary
    key,
    sale_date
    date,
    book_id
    bigint
    references
    books
(
    book_id
),
    price decimal
(
    8,
    2
),
    amount bigint,
    client_id bigint references clients
(
    client_id
)
    );

create table if not exists banks
(
    bank_id
    bigserial
    primary
    key,
    bank_title
    varchar
(
    250
) not null ,
    bank_address varchar
(
    250
) not null ,
    phone_number varchar
(
    250
) not null
    );

create table if not exists currencies
(
    currency_id
    bigserial
    primary
    key,
    currency_title
    varchar
(
    250
) not null ,
    designation varchar
(
    250
) not null
    );

create table if not exists accounts
(
    account_id
    bigserial
    primary
    key,
    account_number
    varchar
(
    250
) not null ,
    bank_id bigint references banks
(
    bank_id
),
    currency_id bigint references currencies
(
    currency_id
)
    );

create table if not exists suppliers
(
    supplier_id
    bigserial
    primary
    key,
    supplier_name
    varchar
(
    250
) not null ,
    account_id bigint references accounts
(
    account_id
)
    );

create table if not exists supplies
(
    supply_id
    bigserial
    primary
    key,
    supply_date
    date,
    supplier_id
    bigint
    references
    suppliers
(
    supplier_id
)
    );

create table if not exists supply_details
(
    supply_detail_id
    bigserial
    primary
    key,
    book_id
    bigint
    references
    books
(
    book_id
),
    price decimal
(
    8,
    2
),
    amount bigint,
    supply_id bigint references supplies
(
    supply_id
)
    );