Use master
go

Create DATABASE JavaWebNotino
go

use JavaWebNotino
GO



create table [Role]
(
    IDRole int not null primary key identity(1,1),
    [Type] nvarchar(50) not null
)
go

create table LoginCred
(
    IDLoginCred int not null primary key identity(1,1),
    UserName nvarchar(30) not null,
    [Password] nvarchar(30) not null
)
go



create table UserAdress
(
    IDUserAdress int not null primary key identity(1,1),
    Adress nvarchar(30) not null,
    PostalCode nvarchar(30) not null,
    City nvarchar(30) not null
)
go




create table [User]
(
    IDUser int not null primary key identity(1,1),
    [Name] nvarchar(50) not null,
    Surname nvarchar(50) not null,
    RoleID int not null references Role(IDRole),
    LoginCredID int not null references LoginCred(IDLoginCred),
    UserAdressID int not null references UserAdress(IDUserAdress)
)
go




create table ProductType
(
    IDProductType int not null primary key identity(1,1),
    [Type] nvarchar(50) not null
)
go


create table Product
(
    IDProduct int not null primary key identity(1,1),
    ProductTypeID int not null references ProductType(IDProductType),
    Maker nvarchar(50) not null,
    ProductName nvarchar(50) not null,
    ShortDescription nvarchar(30) not null,
    Price int not null,
    Img varbinary(max)
)
go


create table Cart
(
    IDCart int not null primary key identity(1,1),
    UserID int not null references [User](IDUser),
    ProductID int not null references Product(IDProduct),
    Quantity int not null
)
go


create table [Order]
(
    IDOrder int not null primary key identity(1,1),
    UserID int not null references [User](IDUser),
    Adress nvarchar(50) not null,
    Payment nvarchar(30) not null,
    [Date] NVARCHAR(30) not null,
    Price float not null
)
go


create table OrderItems
(
    IDOrderItems int not null primary key identity(1,1),
    ProductID int not null references Product(IDProduct),
    Quantity int not null,
    OrderID int not null references  [Order](IDOrder)
)
go



create table LoginInfo
(
LoginInfoID int not null primary key identity(1,1),
UserName nvarchar(50) not null,
DateAndTime nvarchar(50),
IPAdress nvarchar(50)
)

go

create proc GetAllUsers
AS
BEGIN
SELECT * from [User]
END
GO





create proc GetAllLoginInfo
AS
BEGIN
SELECT * from LoginInfo
END
GO


create proc InsertLoginInfo
@UserName nvarchar(50),
@DateAndTime nvarchar(50),
@IPAdress nvarchar(50)
AS
BEGIN

Insert into LoginInfo(UserName,DateAndTime,IPAdress) VALUES(@UserName,@DateAndTime,@IPAdress)

END
go


create proc InsertProduct
@IDType int,
@Maker nvarchar(50),
@ProductName nvarchar(50),
@Shortdes nvarchar(50),
@Price int,
@Img varbinary(max)
AS
BEGIN

Insert into Product(ProductTypeID,Maker,ProductName,ShortDescription,Price,Img) VALUES(@IDType,@Maker,@ProductName,@Shortdes,@Price,@Img)

END
go




create proc UpdateProduct
@ID int,
@IDType int,
@Maker nvarchar(50),
@ProductName nvarchar(50),
@Shortdes nvarchar(50),
@Price int,
@Img varbinary(max)
AS
BEGIN

Update Product set ProductTypeID = @IDType, Maker = @Maker, ProductName = @ProductName, ShortDescription = @Shortdes, Price = @Price, Img = @Img
where IDProduct = @ID

END
go

create proc UpdateProductSameImage
@ID int,
@IDType int,
@Maker nvarchar(50),
@ProductName nvarchar(50),
@Shortdes nvarchar(50),
@Price int
AS
BEGIN

Update Product set ProductTypeID = @IDType, Maker = @Maker, ProductName = @ProductName, ShortDescription = @Shortdes, Price = @Price
where IDProduct = @ID

END
go


create proc DeleteProduct
@ID int
AS
BEGIN
DELETE from OrderItems where ProductID = @ID
DELETE from [Cart] WHERE ProductID = @ID
DELETE from Product WHERE IDProduct = @ID
END
go



create proc GetAllOrders
AS
BEGIN
SELECT * from [Order]
END
go




create proc GetPastOrder
@IDOrder int
as
BEGIN
select t.ProductID, t.Quantity from OrderItems as t 
inner join [Order] as o on t.OrderID = o.IDOrder
where o.IDOrder = @IDOrder
END
GO




create proc getOrderID
@ID int
AS
BEGIN
Select top 1 o.IDOrder from [ORDER] as o
where o.UserID = @ID
ORDER by IDOrder  desc
END
go

create proc InsertOrder
@UserID int,
@Adress nvarchar(50),
@Payment nvarchar(30),
@Date nvarchar(30),
@Price float
AS
BEGIN
Insert into [Order](UserID,Adress,Payment,[Date],Price) values(@UserID,@Adress,@Payment,@Date,@Price)
END
go

create proc InsertOrderProducts
    @ProductID int,
    @Quantity int,
    @IDOrder int
AS
BEGIN
    INSERT into OrderItems
        (ProductID,Quantity,OrderID)
    VALUES(@ProductID, @Quantity, @IDOrder )
END
go



create proc GetOrders
    @UserID int
AS
BEGIN
    select *
    from [Order] as c
    where c.UserID = @UserID
END
go



create proc DeleteOrder
    @OrderID int
AS
BEGIN
DELETE from OrderItems where OrderID = @OrderID
DELETE from [Order] WHERE IDOrder = @OrderID
END
go




create proc CartInsert
    @UserID int,
    @ProductID int,
    @Quantity int
AS
BEGIN
    INSERT into Cart
        (UserID,ProductID,Quantity)
    VALUES(@UserID, @ProductID, @Quantity)
END
go

create proc GetCartProducts
    @UserID int
AS
BEGIN
    select c.ProductID, c.Quantity
    from Cart as c
    where c.UserID = @UserID
END
go


create proc CartReset
    @UserID int
AS
BEGIN
    Delete Cart WHERE Cart.UserID = @UserID
END

go


exec DeleteAll
exec DataFill
go

create proc DeleteAll
AS
BEGIN
    DELETE from LoginInfo
    DELETE from OrderItems
    DELETE from [Order]
    DELETE from Cart
    DELETE from [Product]
    DELETE from [User]
    DELETE from [Role]
    DELETE from LoginCred
    DELETE from [ProductType]
    Delete from UserAdress

    DBCC CHECKIDENT (LoginInfo, RESEED, 0)
    DBCC CHECKIDENT (OrderItems, RESEED, 0)
    DBCC CHECKIDENT ([ORDER], RESEED, 0)
    DBCC CHECKIDENT (Cart, RESEED, 0)
    DBCC CHECKIDENT ([User], RESEED, 0)
    DBCC CHECKIDENT ([Role], RESEED, 0)
    DBCC CHECKIDENT (LoginCred, RESEED, 0)
    DBCC CHECKIDENT ( [ProductType], RESEED, 0)
    DBCC CHECKIDENT ( Product, RESEED, 0)
    DBCC CHECKIDENT ( UserAdress, RESEED, 0)

END
go


create proc  ValidateUser
    @UserName NVARCHAR(30),
    @Password NVARCHAR(30)
AS
BEGIN
    SET NOCOUNT ON;
    DECLARE @UserID INT,  @RoleID INT

    SELECT @UserID = u.IDUser, @RoleID = u.RoleID
    FROM [User] as u inner join LoginCred as l on l.IDLoginCred = u.LoginCredID
    WHERE UserName = @UserName AND [Password] = @Password

    IF @UserID IS NOT NULL
      BEGIN

        select u.IDUser, u.name, u.Surname, u.RoleID
        from [User] as u
        WHERE IDUser = @UserID-- User Valid

    END
      ELSE
      BEGIN
        SELECT null
    --User invalid
    END
END
go


create proc DataFill
as
BEGIN

    insert into [Role]
        ([Type])
    VALUES('Admin')
    insert into [Role]
        ([Type])
    VALUES('User')

    INSERT into [UserAdress]
        (Adress,City,PostalCode)
    VALUES('Jurja ves 111', 'Zagreb', '10000')
    insert into LoginCred
        (UserName,[Password])
    VALUEs('Admin', 'Admin1')
    insert into [User]
        ([Name],Surname,RoleID,LoginCredID,UserAdressID)
    VALUES('Admin', 'Adminovic', 1, 1, 1)

    INSERT into [UserAdress]
        (Adress,City,PostalCode)
    VALUES('Ilica 282', 'Zagreb', '10000')
    insert into LoginCred
        (UserName,[Password])
    VALUEs('Pero', 'Peric')
    insert into [User]
        ([Name],Surname,RoleID,LoginCredID,UserAdressID)
    VALUES('Pero', 'Peric', 2, 2, 2)

    INSERT into [UserAdress]
        (Adress,City,PostalCode)
    VALUES('Trg Bana Josipa Jelacica', 'Zagreb', '10000')
    insert into LoginCred
        (UserName,[Password])
    VALUEs('Ivo', 'Ivic')
    insert into [User]
        ([Name],Surname,RoleID,LoginCredID,UserAdressID)
    VALUES('Ivo', 'Ivic', 2, 3, 3)



    INSERT into ProductType
        ([Type])
    VALUES('Parfemi')
    INSERT into ProductType
        ([Type])
    VALUES('Kosa')
    INSERT into ProductType
        ([Type])
    VALUES('Zubi')


    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(1, 'Maison Margiela', 'REPLICA By The Fireplace', 'Eau de Toillete Unisex 100 ml', 718, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\fireplace.jpeg', SINGLE_BLOB) as T1))
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(1, 'Chloe', 'Chloe', 'Eau de Toillete Women 100 ml', 603, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\chloe.jpeg', SINGLE_BLOB) as T1))
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(1, 'Hugo Bos', 'Boss Bottled', 'Eau de Toillete for Men 100 ml', 440, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Boss bottled.jpeg' , SINGLE_BLOB) as T1))
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(1, 'Montblanc', 'Explorer', 'Eau de Parfum for Men 60 ml', 302, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Explorer.jpeg' , SINGLE_BLOB) as T1))

    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(2, 'Nivea', 'Men Protect & Care', 'Gel za tusiranje 500 ml', 23, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Nivea.jpg' , SINGLE_BLOB) as T1))
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(2, 'Kallos', 'KJMN', 'sampon s keratinom 1000 ml', 20, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Kallos.jpg' , SINGLE_BLOB) as T1))
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(2, 'Natura Siberica', 'For men only', 'Sampon za kosu', 41, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Natura siberica.jpg' , SINGLE_BLOB) as T1))
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(2, 'Dove', 'DermaCare Scalp', 'Sampon protiv peruti 400 ml', 29, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Dove.jpg' , SINGLE_BLOB) as T1))


            
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(3, 'Listerine', 'Total Teeth Protection', 'Vodica za usta', 9, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Listerine.jpg' , SINGLE_BLOB) as T1))
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(3, 'Colgate', 'Whitening', 'Pasta za zube 100 ml', 20, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Colgate.jpg' , SINGLE_BLOB) as T1))
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(3, 'Oral B', 'Essencial Floss', 'Zubni konac 50 m', 15, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Oralb.jpg' , SINGLE_BLOB) as T1))
    INSERT into Product
        (ProductTypeID,Maker,ProductName,ShortDescription,Price,Img)
    VALUES(3, 'Curaprox', '5460 Ultra Soft', 'Cetkica za zube soft 3 kom', 29, (SELECT *
            FROM OPENROWSET(BULK 'C:\Users\tsold\Desktop\FrontEnd Notino\img\Curaprox.jpg' , SINGLE_BLOB) as T1))
    

end
go

SELECT * from Product

go


create proc GetProducts
as
BEGIN
    select *
    from Product
end
go

create proc GetSpecificProduct
    @ProductID INT
AS
BEGIN
    SELECT *
    from Product
    where Product.IDProduct = @ProductID
END
GO

create proc GetProductsByCategory
    @Category INT
as
BEGIN
    select *
    from Product
    where Product.ProductTypeID = @Category
END
go

create proc GetAllCategories
as
BEGIN
    SELECT *
    from ProductType
END
go

create proc InsertCategory
@Type NVARCHAR(30)
as
BEGIN
insert into ProductType([Type]) VALUES(@Type)
END
GO

create proc DeleteCategory
@ID INT
as
BEGIN
Delete from Product WHERE ProductTypeID = @ID
Delete from ProductType where IDProductType = @ID
END
go