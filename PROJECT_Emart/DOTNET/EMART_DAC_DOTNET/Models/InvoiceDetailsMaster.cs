using System;
using System.Collections.Generic;

namespace EMART_DAC.Models;

public partial class InvoiceDetailsMaster
{
    public int InvoiceDtId { get; set; }

    public double CardHolderPrice { get; set; }

    public int PointsRedeem { get; set; }

    public int Prodid { get; set; }

    public int Invoiceid { get; set; }

    public double Mrp { get; set; }

    public string? ProdName { get; set; }

    public InvoiceMaster? Invoice { get; set; }

    public  ProductMaster? Prod { get; set; }
}
