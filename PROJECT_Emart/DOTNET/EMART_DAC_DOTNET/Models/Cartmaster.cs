using System;
using System.Collections.Generic;

namespace EMART_DAC.Models;

public partial class Cartmaster
{
    public int CartId { get; set; }

    public int Custid { get; set; }

    public int Prodid { get; set; }

    public int Qty { get; set; }

    public Customermaster? Cust { get; set; }

    public ProductMaster? Prod { get; set; }

}