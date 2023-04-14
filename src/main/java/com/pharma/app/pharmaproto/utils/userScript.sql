create table if not exists public.users
(
    user_id       serial,
    nom           varchar(100)             not null,
    prenom        varchar(100)             not null,
    adresse       varchar(100)             not null,
    email         varchar(100)             not null,
    tel_no        varchar(100)             not null,
    date_creation timestamp with time zone not null,
    derniere_conn timestamp with time zone,
    present       boolean                  not null,
    statut        boolean                  not null,
    username      varchar(50)              not null,
    password      varchar(100),
    primary key (user_id)
);

-- alter table public.users
--     owner to postgres;

create table if not exists public.roles
(
    role_id     serial,
    titre       varchar(20) not null,
    description varchar(150),
    primary key (role_id)
);

-- alter table public.roles
--     owner to postgres;

create table if not exists public.users_roles
(
    user_id     integer not null,
    role_id     integer not null,
    date_attrib timestamp with time zone,
    id          serial,
    primary key (id),
    foreign key (user_id) references public.users,
    foreign key (role_id) references public.roles
);

--alter table public.users_roles
--    owner to postgres;

