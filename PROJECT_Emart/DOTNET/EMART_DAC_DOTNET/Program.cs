
using EMART_DAC.Controllers;
using EMART_DAC.DAL.Implementations;
using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models;
using EMART_DAC.Models.DTO;
using Microsoft.EntityFrameworkCore;
using System.Configuration;

namespace EMART_DAC
{
    public class Program
    {
        public static void Main(string[] args)
        
        {
            var builder = WebApplication.CreateBuilder(args);

            // Add services to the container.
            builder.Services.AddDbContext<MyDbContext>(options =>
            options.UseMySQL(builder.Configuration.GetConnectionString("DefaultConnection")),ServiceLifetime.Transient);

            builder.Services.AddControllers();
            // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();
            builder.Services.AddScoped<ICustomermasterRepository, CustomermasterRepository>();
            builder.Services.AddScoped<ICartmasterRepository, CartmasterRepository>();
            builder.Services.AddScoped<IInvoiceMasterRepository, InvoiceMasterRepository>();
            builder.Services.AddScoped<ICategoryMasterRepository, CategoryMasterRepository>();
            builder.Services.AddScoped<IConfigDetailMasterRepository, ConfigDetailMasterRepository>();
            builder.Services.AddScoped<IConfigMasterRepository, ConfigMasterRepository>();
            builder.Services.AddScoped<IInvoiceDetailsMasterRepository, InvoiceDetailsMasterRepository>();
            builder.Services.AddScoped<IOrderMasterRepository, OrderMasterRepository>();
            builder.Services.AddScoped<IProductMasterRepository, ProductMasterRepository>();
           // builder.Services.AddScoped<IEmailService, EmailService>();
            //builder.Services.AddScoped<ProductPDFExporter>();
            builder.Services.Configure<SmtpSettings>(builder.Configuration.GetSection("SmtpSettings"));
            
            builder.Services.AddCors(
           (p) => p.AddDefaultPolicy(policy => policy.WithOrigins("*").AllowAnyHeader().AllowAnyMethod()));

            var app = builder.Build();

            // Configure the HTTP request pipeline.
            if (app.Environment.IsDevelopment())
            {
                app.UseSwagger();
                app.UseSwaggerUI();
            }

            app.UseHttpsRedirection();

            app.UseAuthorization();

            app.UseCors();

            app.MapControllers();

            app.Run();
        }
    }
}