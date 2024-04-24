using System;
using System.Collections.Generic;

namespace EMART_DAC.Models;

public partial class OrderMaster
{
    public int OrderId { get; set; }

    public int Custid { get; set; }

    public DateTime? Deliverydate { get; set; }

    public DateTime? OrderDate { get; set; }

    public string? ShippingAdd { get; set; }

    public int Invoiceid { get; set; }

    public virtual Customermaster Cust { get; set; } = null!;

    public virtual InvoiceMaster Invoice { get; set; } = null!;
}
