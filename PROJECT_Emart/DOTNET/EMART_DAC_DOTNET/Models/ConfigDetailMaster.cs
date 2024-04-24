using System;
using System.Collections.Generic;

namespace EMART_DAC.Models;

public partial class ConfigDetailMaster
{
    public int ConfigDetailsid { get; set; }

    public int Configid { get; set; }

    public string? ConfigDetails { get; set; }

    public int Prodid { get; set; }

    public virtual ConfigMaster Config { get; set; } = null!;

    public virtual ProductMaster Prod { get; set; } = null!;
}
