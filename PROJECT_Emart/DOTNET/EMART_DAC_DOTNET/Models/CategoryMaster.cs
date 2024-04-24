using System;
using System.Collections.Generic;

namespace EMART_DAC.Models;

public partial class CategoryMaster
{
    public int CatmasterId { get; set; }

    public string? CatImgPath { get; set; }

    public string? CategoryName { get; set; }

    public ulong Childflag { get; set; }

    public int ParentCatid { get; set; }

    public virtual ICollection<ProductMaster> ProductMasters { get; } = new List<ProductMaster>();
}
