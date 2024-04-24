using System;
using Microsoft.EntityFrameworkCore.Migrations;
using MySql.EntityFrameworkCore.Metadata;

#nullable disable

namespace EMARTDAC.Migrations
{
    /// <inheritdoc />
    public partial class Latest : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterDatabase()
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "category_master",
                columns: table => new
                {
                    catmasterid = table.Column<int>(name: "catmaster_id", type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    catimgpath = table.Column<string>(name: "cat_img_path", type: "varchar(255)", maxLength: 255, nullable: true),
                    categoryname = table.Column<string>(name: "category_name", type: "varchar(255)", maxLength: 255, nullable: true),
                    childflag = table.Column<ulong>(type: "bit(1)", nullable: false),
                    parentcatid = table.Column<int>(name: "parent_catid", type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.catmasterid);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "config_master",
                columns: table => new
                {
                    configid = table.Column<int>(name: "config_id", type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    configname = table.Column<string>(name: "config_name", type: "varchar(255)", maxLength: 255, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.configid);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "customermaster",
                columns: table => new
                {
                    custid = table.Column<int>(name: "cust_id", type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    cardholder = table.Column<ulong>(name: "card_holder", type: "bit(1)", nullable: false),
                    custaddress = table.Column<string>(name: "cust_address", type: "varchar(255)", maxLength: 255, nullable: false),
                    custemail = table.Column<string>(name: "cust_email", type: "varchar(255)", maxLength: 255, nullable: true),
                    custname = table.Column<string>(name: "cust_name", type: "varchar(255)", maxLength: 255, nullable: false),
                    custpassword = table.Column<string>(name: "cust_password", type: "varchar(255)", maxLength: 255, nullable: false),
                    custphone = table.Column<string>(name: "cust_phone", type: "varchar(255)", maxLength: 255, nullable: false),
                    points = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.custid);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "product_master",
                columns: table => new
                {
                    prodid = table.Column<int>(name: "prod_id", type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    disc = table.Column<int>(type: "int", nullable: true, defaultValueSql: "'0'"),
                    cardholderprice = table.Column<double>(name: "card_holder_price", type: "double", nullable: false),
                    catmasterid = table.Column<int>(type: "int", nullable: false),
                    imgpath = table.Column<string>(type: "varchar(255)", maxLength: 255, nullable: false),
                    inventoryquantity = table.Column<int>(name: "inventory_quantity", type: "int", nullable: false),
                    mrpprice = table.Column<double>(name: "mrp_price", type: "double", nullable: false),
                    offerprice = table.Column<double>(name: "offer_price", type: "double", nullable: false),
                    pointsredeem = table.Column<int>(name: "points_redeem", type: "int", nullable: false),
                    prodlongdesc = table.Column<string>(name: "prod_long_desc", type: "varchar(255)", maxLength: 255, nullable: false),
                    prodname = table.Column<string>(name: "prod_name", type: "varchar(255)", maxLength: 255, nullable: false),
                    prodshortdesc = table.Column<string>(name: "prod_short_desc", type: "varchar(255)", maxLength: 255, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.prodid);
                    table.ForeignKey(
                        name: "FK7l27rit3nf8l5qmep0gvi3j4d",
                        column: x => x.catmasterid,
                        principalTable: "category_master",
                        principalColumn: "catmaster_id");
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "invoice_master",
                columns: table => new
                {
                    invoiceid = table.Column<int>(name: "invoice_id", type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    invoicedate = table.Column<DateTime>(name: "invoice_date", type: "date", nullable: true),
                    totalbill = table.Column<double>(name: "total_bill", type: "double", nullable: false),
                    custid = table.Column<int>(type: "int", nullable: false),
                    deliverycharge = table.Column<double>(name: "delivery_charge", type: "double", nullable: false),
                    tax = table.Column<double>(type: "double", nullable: false),
                    totalamt = table.Column<double>(name: "total_amt", type: "double", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.invoiceid);
                    table.ForeignKey(
                        name: "FK7u0t7n1mya9ncro1bp50tgdq4",
                        column: x => x.custid,
                        principalTable: "customermaster",
                        principalColumn: "cust_id");
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "cartmaster",
                columns: table => new
                {
                    cartid = table.Column<int>(name: "cart_id", type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    custid = table.Column<int>(type: "int", nullable: false),
                    prodid = table.Column<int>(type: "int", nullable: false),
                    qty = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.cartid);
                    table.ForeignKey(
                        name: "FKa4x5t1elnol7fw9dkgwo1rjl5",
                        column: x => x.prodid,
                        principalTable: "product_master",
                        principalColumn: "prod_id");
                    table.ForeignKey(
                        name: "FKarshbmm2wr9ypr7fhui450isf",
                        column: x => x.custid,
                        principalTable: "customermaster",
                        principalColumn: "cust_id");
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "config_detail_master",
                columns: table => new
                {
                    configdetailsid = table.Column<int>(name: "config_detailsid", type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    configid = table.Column<int>(type: "int", nullable: false),
                    configdetails = table.Column<string>(name: "config_details", type: "varchar(255)", maxLength: 255, nullable: true),
                    prodid = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.configdetailsid);
                    table.ForeignKey(
                        name: "FK9xrma2d2c6tmn9kfisoju6c4m",
                        column: x => x.prodid,
                        principalTable: "product_master",
                        principalColumn: "prod_id");
                    table.ForeignKey(
                        name: "FKiuq1carlv822tcbnx5bf6wus6",
                        column: x => x.configid,
                        principalTable: "config_master",
                        principalColumn: "config_id");
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "invoice_details_master",
                columns: table => new
                {
                    invoicedtid = table.Column<int>(name: "invoice_dt_id", type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    cardholderprice = table.Column<double>(name: "card_holder_price", type: "double", nullable: false),
                    pointsredeem = table.Column<int>(name: "points_redeem", type: "int", nullable: false),
                    prodid = table.Column<int>(type: "int", nullable: false),
                    invoiceid = table.Column<int>(type: "int", nullable: false),
                    mrp = table.Column<double>(type: "double", nullable: false),
                    prodname = table.Column<string>(name: "prod_name", type: "varchar(255)", maxLength: 255, nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.invoicedtid);
                    table.ForeignKey(
                        name: "FKpgk06x492m3k7h3it31vrlg54",
                        column: x => x.invoiceid,
                        principalTable: "invoice_master",
                        principalColumn: "invoice_id");
                    table.ForeignKey(
                        name: "FKpj8eh8y2k7hlenkf24k74423g",
                        column: x => x.prodid,
                        principalTable: "product_master",
                        principalColumn: "prod_id");
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "order_master",
                columns: table => new
                {
                    orderid = table.Column<int>(name: "order_id", type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    custid = table.Column<int>(type: "int", nullable: false),
                    deliverydate = table.Column<DateTime>(type: "date", nullable: true),
                    orderdate = table.Column<DateTime>(name: "order_date", type: "date", nullable: true),
                    shippingadd = table.Column<string>(name: "shipping_add", type: "varchar(255)", maxLength: 255, nullable: true),
                    invoiceid = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PRIMARY", x => x.orderid);
                    table.ForeignKey(
                        name: "FKgnu6ib5f4qddkkkqj1k19hoo7",
                        column: x => x.invoiceid,
                        principalTable: "invoice_master",
                        principalColumn: "invoice_id");
                    table.ForeignKey(
                        name: "FKtb5490ctvm66hht5e0rfpyih0",
                        column: x => x.custid,
                        principalTable: "customermaster",
                        principalColumn: "cust_id");
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateIndex(
                name: "FKa4x5t1elnol7fw9dkgwo1rjl5",
                table: "cartmaster",
                column: "prodid");

            migrationBuilder.CreateIndex(
                name: "UKeum13wouai95dogaxypbfm5m5",
                table: "cartmaster",
                columns: new[] { "custid", "prodid" },
                unique: true);

            migrationBuilder.CreateIndex(
                name: "FK9xrma2d2c6tmn9kfisoju6c4m",
                table: "config_detail_master",
                column: "prodid");

            migrationBuilder.CreateIndex(
                name: "FKiuq1carlv822tcbnx5bf6wus6",
                table: "config_detail_master",
                column: "configid");

            migrationBuilder.CreateIndex(
                name: "FKpgk06x492m3k7h3it31vrlg54",
                table: "invoice_details_master",
                column: "invoiceid");

            migrationBuilder.CreateIndex(
                name: "FKpj8eh8y2k7hlenkf24k74423g",
                table: "invoice_details_master",
                column: "prodid");

            migrationBuilder.CreateIndex(
                name: "FK7u0t7n1mya9ncro1bp50tgdq4",
                table: "invoice_master",
                column: "custid");

            migrationBuilder.CreateIndex(
                name: "FKgnu6ib5f4qddkkkqj1k19hoo7",
                table: "order_master",
                column: "invoiceid");

            migrationBuilder.CreateIndex(
                name: "FKtb5490ctvm66hht5e0rfpyih0",
                table: "order_master",
                column: "custid");

            migrationBuilder.CreateIndex(
                name: "FK7l27rit3nf8l5qmep0gvi3j4d",
                table: "product_master",
                column: "catmasterid");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "cartmaster");

            migrationBuilder.DropTable(
                name: "config_detail_master");

            migrationBuilder.DropTable(
                name: "invoice_details_master");

            migrationBuilder.DropTable(
                name: "order_master");

            migrationBuilder.DropTable(
                name: "config_master");

            migrationBuilder.DropTable(
                name: "product_master");

            migrationBuilder.DropTable(
                name: "invoice_master");

            migrationBuilder.DropTable(
                name: "category_master");

            migrationBuilder.DropTable(
                name: "customermaster");
        }
    }
}
