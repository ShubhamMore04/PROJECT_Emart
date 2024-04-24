using System;
using System.Collections.Generic;

namespace EMART_DAC.Models;

public partial class ConfigMaster
{
    public int ConfigId { get; set; }

    public string? ConfigName { get; set; }

    public virtual ICollection<ConfigDetailMaster> ConfigDetailMasters { get; } = new List<ConfigDetailMaster>();
}
