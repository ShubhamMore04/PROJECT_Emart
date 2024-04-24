using System;
using System.Collections.Generic;

namespace EMART_DAC.Models;

public partial class InvoiceMaster
{
    public int InvoiceId { get; set; }

    public DateTime? InvoiceDate { get; set; }

    public double TotalBill { get; set; }

    public int Custid { get; set; }

    public double DeliveryCharge { get; set; }

    public double Tax { get; set; }

    public double TotalAmt { get; set; }

    public Customermaster? Cust { get; set; }

    public ICollection<InvoiceDetailsMaster> InvoiceDetailsMasters { get; } = new List<InvoiceDetailsMaster>();

    public ICollection<OrderMaster> OrderMasters { get; } = new List<OrderMaster>();
}
