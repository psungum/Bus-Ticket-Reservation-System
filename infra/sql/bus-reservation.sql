use busreservation;

-- Table Bus :
--  ====================
create table bus(
    busNo int primary key,
    bName varchar(20) not null unique,
    routeFrom varchar(20) not null,
    routeTo varchar(20) not null,
    bType varchar(5) not null check (bType = 'AC' OR bType = 'NonAC'),
    departure Datetime not null,
    arrival Datetime not null,
    totalSeats int not null,
    availSeats int not null,
    fare int not null
);

-- Table Customer :
-- =====================
create table customer (
    cusId int primary key auto_increment,
    username varchar(20) not null unique,
    password varchar(20) not null,
    firstName varchar(20) not null,
    lastName varchar(20) not null,
    address varchar(20) not null,
    mobile varchar(12) not null
);

-- Table Booking :
-- ==================
create table booking (
    bId int primary key auto_increment,
    cusId int not null,
    busNo int not null,
    seatFrom int not null,
    seatTo int not null,
    status boolean default false,
    foreign key (cusId) references customer(cusId),
    foreign key (busNo) references bus(busNo)
);

-- Customer and Bus has many to many relationship.
-- Using Booking table as linking between Customer and Bus.
