using System;
using System.Collections.Generic;

namespace EMART_DAC.Models;

public partial class Customermaster
{
    public int CustId { get; set; }

    public bool CardHolder { get; set; }

    public string CustAddress { get; set; } = null!;

    public string? CustEmail { get; set; }

    public string CustName { get; set; } = null!;

    public string CustPassword { get; set; } = null!;

    public string CustPhone { get; set; } = null!;

    public int Points { get; set; }

    public virtual ICollection<Cartmaster> Cartmasters { get; } = new List<Cartmaster>();

    public virtual ICollection<InvoiceMaster> InvoiceMasters { get; } = new List<InvoiceMaster>();

    public virtual ICollection<OrderMaster> OrderMasters { get; } = new List<OrderMaster>();
}
