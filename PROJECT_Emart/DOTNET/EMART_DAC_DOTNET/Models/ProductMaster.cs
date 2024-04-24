using System;
using System.Collections.Generic;

namespace EMART_DAC.Models;

public partial class ProductMaster
{
    public int ProdId { get; set; }

    public int? Disc { get; set; }

    public double CardHolderPrice { get; set; }

    public int Catmasterid { get; set; }

    public string Imgpath { get; set; } = null!;

    public int InventoryQuantity { get; set; }

    public double MrpPrice { get; set; }

    public double OfferPrice { get; set; }

    public int PointsRedeem { get; set; }

    public string ProdLongDesc { get; set; } = null!;

    public string ProdName { get; set; } = null!;

    public string ProdShortDesc { get; set; } = null!;

    public virtual ICollection<Cartmaster> Cartmasters { get; } = new List<Cartmaster>();

    public virtual CategoryMaster Catmaster { get; set; } = null!;

    public virtual ICollection<ConfigDetailMaster> ConfigDetailMasters { get; } = new List<ConfigDetailMaster>();

    public virtual ICollection<InvoiceDetailsMaster> InvoiceDetailsMasters { get; } = new List<InvoiceDetailsMaster>();
}
