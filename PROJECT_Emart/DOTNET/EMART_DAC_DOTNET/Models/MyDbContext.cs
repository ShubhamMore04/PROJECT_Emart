using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace EMART_DAC.Models;

public partial class MyDbContext : DbContext
{
    public MyDbContext()
    {
    }

    public MyDbContext(DbContextOptions<MyDbContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Cartmaster> Cartmasters { get; set; }

    public virtual DbSet<CategoryMaster> CategoryMasters { get; set; }

    public virtual DbSet<ConfigDetailMaster> ConfigDetailMasters { get; set; }

    public virtual DbSet<ConfigMaster> ConfigMasters { get; set; }

    public virtual DbSet<Customermaster> Customermasters { get; set; }

    public virtual DbSet<InvoiceDetailsMaster> InvoiceDetailsMasters { get; set; }

    public virtual DbSet<InvoiceMaster> InvoiceMasters { get; set; }

    public virtual DbSet<OrderMaster> OrderMasters { get; set; }

    public virtual DbSet<ProductMaster> ProductMasters { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see http://go.microsoft.com/fwlink/?LinkId=723263.
        => optionsBuilder.UseMySQL("Server=localhost;Database=emart;user id=root;password=Shubham@222");

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Cartmaster>(entity =>
        {
            entity.HasKey(e => e.CartId).HasName("PRIMARY");

            entity.ToTable("cartmaster");

            entity.HasIndex(e => e.Prodid, "FKa4x5t1elnol7fw9dkgwo1rjl5");

            entity.HasIndex(e => new { e.Custid, e.Prodid }, "UKeum13wouai95dogaxypbfm5m5").IsUnique();

            entity.Property(e => e.CartId).HasColumnName("cart_id");
            entity.Property(e => e.Custid).HasColumnName("custid");
            entity.Property(e => e.Prodid).HasColumnName("prodid");
            entity.Property(e => e.Qty).HasColumnName("qty");

            entity.HasOne(d => d.Cust).WithMany(p => p.Cartmasters)
                .HasForeignKey(d => d.Custid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKarshbmm2wr9ypr7fhui450isf");

            entity.HasOne(d => d.Prod).WithMany(p => p.Cartmasters)
                .HasForeignKey(d => d.Prodid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKa4x5t1elnol7fw9dkgwo1rjl5");
        });

        modelBuilder.Entity<CategoryMaster>(entity =>
        {
            entity.HasKey(e => e.CatmasterId).HasName("PRIMARY");

            entity.ToTable("category_master");

            entity.Property(e => e.CatmasterId).HasColumnName("catmaster_id");
            entity.Property(e => e.CatImgPath)
                .HasMaxLength(255)
                .HasColumnName("cat_img_path");
            entity.Property(e => e.CategoryName)
                .HasMaxLength(255)
                .HasColumnName("category_name");
            entity.Property(e => e.Childflag)
                .HasColumnType("bit(1)")
                .HasColumnName("childflag");
            entity.Property(e => e.ParentCatid).HasColumnName("parent_catid");
        });

        modelBuilder.Entity<ConfigDetailMaster>(entity =>
        {
            entity.HasKey(e => e.ConfigDetailsid).HasName("PRIMARY");

            entity.ToTable("config_detail_master");

            entity.HasIndex(e => e.Prodid, "FK9xrma2d2c6tmn9kfisoju6c4m");

            entity.HasIndex(e => e.Configid, "FKiuq1carlv822tcbnx5bf6wus6");

            entity.Property(e => e.ConfigDetailsid).HasColumnName("config_detailsid");
            entity.Property(e => e.ConfigDetails)
                .HasMaxLength(255)
                .HasColumnName("config_details");
            entity.Property(e => e.Configid).HasColumnName("configid");
            entity.Property(e => e.Prodid).HasColumnName("prodid");

            entity.HasOne(d => d.Config).WithMany(p => p.ConfigDetailMasters)
                .HasForeignKey(d => d.Configid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKiuq1carlv822tcbnx5bf6wus6");

            entity.HasOne(d => d.Prod).WithMany(p => p.ConfigDetailMasters)
                .HasForeignKey(d => d.Prodid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK9xrma2d2c6tmn9kfisoju6c4m");
        });

        modelBuilder.Entity<ConfigMaster>(entity =>
        {
            entity.HasKey(e => e.ConfigId).HasName("PRIMARY");

            entity.ToTable("config_master");

            entity.Property(e => e.ConfigId).HasColumnName("config_id");
            entity.Property(e => e.ConfigName)
                .HasMaxLength(255)
                .HasColumnName("config_name");
        });

        modelBuilder.Entity<Customermaster>(entity =>
        {
            entity.HasKey(e => e.CustId).HasName("PRIMARY");

            entity.ToTable("customermaster");

            entity.Property(e => e.CustId).HasColumnName("cust_id");
            entity.Property(e => e.CardHolder)
                .HasColumnType("bit(1)")
                .HasColumnName("card_holder");
            entity.Property(e => e.CustAddress)
                .HasMaxLength(255)
                .HasColumnName("cust_address");
            entity.Property(e => e.CustEmail)
                .HasMaxLength(255)
                .HasColumnName("cust_email");
            entity.Property(e => e.CustName)
                .HasMaxLength(255)
                .HasColumnName("cust_name");
            entity.Property(e => e.CustPassword)
                .HasMaxLength(255)
                .HasColumnName("cust_password");
            entity.Property(e => e.CustPhone)
                .HasMaxLength(255)
                .HasColumnName("cust_phone");
            entity.Property(e => e.Points).HasColumnName("points");
        });

        modelBuilder.Entity<InvoiceDetailsMaster>(entity =>
        {
            entity.HasKey(e => e.InvoiceDtId).HasName("PRIMARY");

            entity.ToTable("invoice_details_master");

            entity.HasIndex(e => e.Invoiceid, "FKpgk06x492m3k7h3it31vrlg54");

            entity.HasIndex(e => e.Prodid, "FKpj8eh8y2k7hlenkf24k74423g");

            entity.Property(e => e.InvoiceDtId).HasColumnName("invoice_dt_id");
            entity.Property(e => e.CardHolderPrice).HasColumnName("card_holder_price");
            entity.Property(e => e.Invoiceid).HasColumnName("invoiceid");
            entity.Property(e => e.Mrp).HasColumnName("mrp");
            entity.Property(e => e.PointsRedeem).HasColumnName("points_redeem");
            entity.Property(e => e.ProdName)
                .HasMaxLength(255)
                .HasColumnName("prod_name");
            entity.Property(e => e.Prodid).HasColumnName("prodid");

            entity.HasOne(d => d.Invoice).WithMany(p => p.InvoiceDetailsMasters)
                .HasForeignKey(d => d.Invoiceid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKpgk06x492m3k7h3it31vrlg54");

            entity.HasOne(d => d.Prod).WithMany(p => p.InvoiceDetailsMasters)
                .HasForeignKey(d => d.Prodid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKpj8eh8y2k7hlenkf24k74423g");
        });

        modelBuilder.Entity<InvoiceMaster>(entity =>
        {
            entity.HasKey(e => e.InvoiceId).HasName("PRIMARY");

            entity.ToTable("invoice_master");

            entity.HasIndex(e => e.Custid, "FK7u0t7n1mya9ncro1bp50tgdq4");

            entity.Property(e => e.InvoiceId).HasColumnName("invoice_id");
            entity.Property(e => e.Custid).HasColumnName("custid");
            entity.Property(e => e.DeliveryCharge).HasColumnName("delivery_charge");
            entity.Property(e => e.InvoiceDate)
                .HasColumnType("date")
                .HasColumnName("invoice_date");
            entity.Property(e => e.Tax).HasColumnName("tax");
            entity.Property(e => e.TotalAmt).HasColumnName("total_amt");
            entity.Property(e => e.TotalBill).HasColumnName("total_bill");

            entity.HasOne(d => d.Cust).WithMany(p => p.InvoiceMasters)
                .HasForeignKey(d => d.Custid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK7u0t7n1mya9ncro1bp50tgdq4");
        });

        modelBuilder.Entity<OrderMaster>(entity =>
        {
            entity.HasKey(e => e.OrderId).HasName("PRIMARY");

            entity.ToTable("order_master");

            entity.HasIndex(e => e.Invoiceid, "FKgnu6ib5f4qddkkkqj1k19hoo7");

            entity.HasIndex(e => e.Custid, "FKtb5490ctvm66hht5e0rfpyih0");

            entity.Property(e => e.OrderId).HasColumnName("order_id");
            entity.Property(e => e.Custid).HasColumnName("custid");
            entity.Property(e => e.Deliverydate)
                .HasColumnType("date")
                .HasColumnName("deliverydate");
            entity.Property(e => e.Invoiceid).HasColumnName("invoiceid");
            entity.Property(e => e.OrderDate)
                .HasColumnType("date")
                .HasColumnName("order_date");
            entity.Property(e => e.ShippingAdd)
                .HasMaxLength(255)
                .HasColumnName("shipping_add");

            entity.HasOne(d => d.Cust).WithMany(p => p.OrderMasters)
                .HasForeignKey(d => d.Custid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKtb5490ctvm66hht5e0rfpyih0");

            entity.HasOne(d => d.Invoice).WithMany(p => p.OrderMasters)
                .HasForeignKey(d => d.Invoiceid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKgnu6ib5f4qddkkkqj1k19hoo7");
        });

        modelBuilder.Entity<ProductMaster>(entity =>
        {
            entity.HasKey(e => e.ProdId).HasName("PRIMARY");

            entity.ToTable("product_master");

            entity.HasIndex(e => e.Catmasterid, "FK7l27rit3nf8l5qmep0gvi3j4d");

            entity.Property(e => e.ProdId).HasColumnName("prod_id");
            entity.Property(e => e.CardHolderPrice).HasColumnName("card_holder_price");
            entity.Property(e => e.Catmasterid).HasColumnName("catmasterid");
            entity.Property(e => e.Disc)
                .HasDefaultValueSql("'0'")
                .HasColumnName("disc");
            entity.Property(e => e.Imgpath)
                .HasMaxLength(255)
                .HasColumnName("imgpath");
            entity.Property(e => e.InventoryQuantity).HasColumnName("inventory_quantity");
            entity.Property(e => e.MrpPrice).HasColumnName("mrp_price");
            entity.Property(e => e.OfferPrice).HasColumnName("offer_price");
            entity.Property(e => e.PointsRedeem).HasColumnName("points_redeem");
            entity.Property(e => e.ProdLongDesc)
                .HasMaxLength(255)
                .HasColumnName("prod_long_desc");
            entity.Property(e => e.ProdName)
                .HasMaxLength(255)
                .HasColumnName("prod_name");
            entity.Property(e => e.ProdShortDesc)
                .HasMaxLength(255)
                .HasColumnName("prod_short_desc");

            entity.HasOne(d => d.Catmaster).WithMany(p => p.ProductMasters)
                .HasForeignKey(d => d.Catmasterid)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK7l27rit3nf8l5qmep0gvi3j4d");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
