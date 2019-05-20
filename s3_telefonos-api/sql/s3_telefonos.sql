delete from TabletEntity_ListaDeDeseosEntity;
delete from SeguroEntity;
delete from CelularEntity_ListaDeDeseosEntity;
delete from CelularEntity;
delete from TabletEntity;
delete from PublicacionEntity_CarritodecomprasEntity;
delete from PublicacionEntity;
delete from MetodoDePagoEntity;
delete from ListaDeDeseosEntity;
delete from FacturaEntity;
delete from ProveedorEntity;
delete from CarritoDeComprasEntity;
delete from CompradorEntity;

insert into CompradorEntity (id, usuario, apodo, contrasenia, correoElectronico) values (1,'cwyre0', 'Courtenay', 'tkJSdCMK', 'cminthorpe0@ebay.co.uk');
insert into CompradorEntity (id, usuario, apodo, contrasenia, correoElectronico) values (2,'rschieferstein1', 'Rhody', '5OcnXwibE15H', 'rhayselden1@goo.gl');
insert into CompradorEntity (id, usuario, apodo, contrasenia, correoElectronico) values (3,'aheight2', 'Arch', 'vgWQVCe7', 'agarstang2@artisteer.com');
insert into CompradorEntity (id, usuario, apodo, contrasenia, correoElectronico) values (4,'kcauldwell3', 'Kimble', 'sWGJmQ04Nz', 'kebbett3@deviantart.com');
insert into CompradorEntity (id, usuario, apodo, contrasenia, correoElectronico) values (5,'astory4', 'Aurelia', 'CIM4N0l', 'agebbie4@multiply.com');

insert into CarritoDeComprasEntity (id, costoTotal, comprador_id) values (1,491981.79, 1);
insert into CarritoDeComprasEntity (id, costoTotal, comprador_id) values (2,359779.0, 2);
insert into CarritoDeComprasEntity (id, costoTotal, comprador_id) values (3, 325134.34, 3);
insert into CarritoDeComprasEntity (id, costoTotal, comprador_id) values (4, 468063.08, 4);
insert into CarritoDeComprasEntity (id, costoTotal, comprador_id) values (5, 253459.12, 5);

insert into ProveedorEntity (id, contrasenia, correoelectronico, nombre, raitig, usuario) values (1, 'Lh4cOnn', 'bthody0@nasa.gov', 'Blakelee Thody', 2, 'bthody0');
insert into ProveedorEntity (id, contrasenia, correoelectronico, nombre, raitig, usuario) values (2, 'oMPsaba7qp', 'hwhitmore1@theguardian.com', 'Hildegarde Whitmore', 5, 'hwhitmore1');
insert into ProveedorEntity (id, contrasenia, correoelectronico, nombre, raitig, usuario) values (3, 'UX5WJLclN3', 'piacovielli2@dailymail.co.uk', 'Piper Iacovielli', 2, 'piacovielli2');
insert into ProveedorEntity (id, contrasenia, correoelectronico, nombre, raitig, usuario) values (4, 'wJzZXFpBR', 'lbosward3@hibu.com', 'Leora Bosward', 2, 'lbosward3');
insert into ProveedorEntity (id, contrasenia, correoelectronico, nombre, raitig, usuario) values (5, 'Gqno8sD', 'baers4@netlog.com', 'Bryan Aers', 2, 'baers4');


insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (1, '5/6/2018', '65841-648', 2, 3);
insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (2, '4/17/2018', '53389-241', 3, 1);
insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (3, '8/16/2018', '37000-819', 1, 4);
insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (4, '3/14/2019', '52959-876', 3, 3);
insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (5, '1/28/2019', '69152-0024', 5, 5);
insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (6, '5/6/2018', '65841-648', 2, 3);
insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (7, '4/17/2018', '53389-241', 3, 1);
insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (8, '8/16/2018', '37000-819', 1, 4);
insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (9, '3/14/2019', '52959-876', 3, 3);
insert into FacturaEntity (id, fecha, referencia, comprador_id, proveedor_id) values (10, '1/28/2019', '69152-0024', 5, 5);

insert into ListaDeDeseosEntity (id, identificador, costoEstimado, comprador_id) values (1, 1, 489407.14, 1);
insert into ListaDeDeseosEntity (id, identificador, costoEstimado, comprador_id) values (2, 2, 55182.5, 2);
insert into ListaDeDeseosEntity (id, identificador, costoEstimado, comprador_id) values (3, 3, 404465.32, 3);
insert into ListaDeDeseosEntity (id, identificador, costoEstimado, comprador_id) values (4, 4, 265382.82, 4);
insert into ListaDeDeseosEntity (id, identificador, costoEstimado, comprador_id) values (5, 5,402231.38, 5);

insert into MetodoDePagoEntity (id, banco, codigoverificacion, comprador_id, fecha, nombre, tipo) values (1, 'Stanton-Wunsch', 3531, 1,CURRENT_DATE, 'Brown brocket', 'jcb');
insert into MetodoDePagoEntity (id, banco, codigoverificacion, comprador_id, fecha, nombre, tipo) values (2, 'Pacocha Inc', 355201, 2, CURRENT_DATE, 'Greater kudu', 'jcb');
insert into MetodoDePagoEntity (id, banco, codigoverificacion, comprador_id, fecha, nombre, tipo) values (3, 'Zulauf, Kirlin and Beier', 356903, 3,CURRENT_DATE, 'Bushbuck', 'jcb');
insert into MetodoDePagoEntity (id, banco, codigoverificacion, comprador_id, fecha, nombre, tipo) values (4, 'Homenick-Hermann', 4917227, 4,CURRENT_DATE, 'Sally lightfoot crab', 'visa-electron');
insert into MetodoDePagoEntity (id, banco, codigoverificacion, comprador_id, fecha, nombre, tipo) values (5, 'Kerluke Inc', 35472631, 5,CURRENT_DATE, 'Penguin, fairy', 'jcb');



insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (1, CURRENT_DATE, null, 372639.03, 4, 1, 0);
insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (2, CURRENT_DATE, null, 295281.08, 5, 2, 0);
insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (3, CURRENT_DATE, null, 108340.20, 2, 3, 0);
insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (4, CURRENT_DATE, null, 321040.51, 1, 4, 0);
insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (5, CURRENT_DATE, null, 272382.42, 2, 5, 0);
insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (6, CURRENT_DATE, null, 152958.56, 5, 6, 0);
insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (7, CURRENT_DATE, null, 239676.21, 2, 7, 0);
insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (8, CURRENT_DATE, null, 557632.32, 4, 8, 0);
insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (9, CURRENT_DATE, null, 259022.8, 2, 9, 0);
insert into PublicacionEntity (id, fechacreacion, imagenes, precio, proveedor_id, factura_id, vendido) values (10, CURRENT_DATE, null, 307387.65, 5, 1, 0);



insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (1, 4);
insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (2, 1);
insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (3, 4);
insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (1, 6);
insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (4, 7);
insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (2, 2);
insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (4, 6);
insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (5, 1);
insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (2, 3);
insert into PublicacionEntity_Carritodecomprasentity (carritodecompras_id, publicaciones_id) values (2, 10);

insert into TabletEntity (id, marca, modelo, referencia, registrado, publicacion_id) values (1,'equate anti nausea', 'Range Rover', '49035-291', 0, 1);
insert into TabletEntity (id, marca, modelo, referencia, registrado, publicacion_id) values (2, 'Amoxicillin', '430', '54868-3105', 1, 2);
insert into TabletEntity (id, marca, modelo, referencia, registrado, publicacion_id) values (3,'Erythrocin Stearate', 'Supra', '54569-0124', 1, 3);
insert into TabletEntity (id, marca, modelo, referencia, registrado, publicacion_id) values (4,'Prograf', 'MKX', '0469-0617', 1, 4);
insert into TabletEntity (id, marca, modelo, referencia, registrado, publicacion_id) values (5,'Givenchy Photo Perfexion Fluid Foundation Broad Spectrum SPF 20 Perfect Beige', 'Wrangler', '60905-0010', 1, 5);
insert into TabletEntity (id, marca, modelo, referencia, registrado, publicacion_id) values (6,'cough', 'Element', '41250-516', 1, 6);
insert into TabletEntity (id, marca, modelo, referencia, registrado, publicacion_id) values (7,'Cultivated Rye', 'Justy', '36987-2372', 1, 7);
insert into TabletEntity (id, marca, modelo, referencia, registrado, publicacion_id) values (8,'Benicar Hct', 'Grand Vitara', '54868-5078', 0, 8);

insert into CelularEntity (id, marca, modelo, referencia, imei, registrado, publicacion_id) values (1, 'EZ-OX', 'G6', '64735-100', 11, 0, 6);
insert into CelularEntity (id, marca, modelo, referencia, imei, registrado, publicacion_id) values (2, 'Dr. Wise Special Formula', 'Truck', '51096-0005', 34, 0, 7);
insert into CelularEntity (id, marca, modelo, referencia, imei, registrado, publicacion_id) values (3, 'Estradiol', '3500', '0378-3352', 8, 1, 8);
insert into CelularEntity (id, marca, modelo, referencia, imei, registrado, publicacion_id) values (4, 'bareMinerals bareSkin Pure Brightening Serum Foundation Broad Spectrum SPF 20', '1 Series', '98132-732', 29, 0, 9);
insert into CelularEntity (id, marca, modelo, referencia, imei, registrado, publicacion_id) values (5, 'Overwhelmed', 'Celica', '50845-0053', 21, 0, 10);

insert into CelularEntity_Listadedeseosentity (celulares_id, listasdedeseos_id) values (4, 1);
insert into CelularEntity_Listadedeseosentity (celulares_id, listasdedeseos_id) values (5, 3);
insert into CelularEntity_Listadedeseosentity (celulares_id, listasdedeseos_id) values (1, 5);
insert into CelularEntity_Listadedeseosentity (celulares_id, listasdedeseos_id) values (3, 4);
insert into CelularEntity_Listadedeseosentity (celulares_id, listasdedeseos_id) values (2, 2);


insert into SeguroEntity (id, aseguradora, monto, proveedor_id) values (1, 'Hayes-Krajcik', 848442.62, 4);
insert into SeguroEntity (id, aseguradora, monto, proveedor_id) values (2, 'Reinger-Franecki', 354828.48, 4);
insert into SeguroEntity (id, aseguradora, monto, proveedor_id) values (3, 'Schinner, Tillman and Medhurst', 587781.24, 2);
insert into SeguroEntity (id, aseguradora, monto, proveedor_id) values (4, 'Hudson Inc', 1070992.18, 3);
insert into SeguroEntity (id, aseguradora, monto, proveedor_id) values (5, 'Conn-Ondricka', 485782.91, 4);

insert into TabletEntity_ListadedeseosEntity (tablets_id, listasdedeseos_id) values (5, 3);
insert into TabletEntity_ListadedeseosEntity (tablets_id, listasdedeseos_id) values (5, 4);
insert into TabletEntity_ListadedeseosEntity (tablets_id, listasdedeseos_id) values (2, 1);
insert into TabletEntity_ListadedeseosEntity (tablets_id, listasdedeseos_id) values (1, 3);
insert into TabletEntity_ListadedeseosEntity (tablets_id, listasdedeseos_id) values (1, 5);

