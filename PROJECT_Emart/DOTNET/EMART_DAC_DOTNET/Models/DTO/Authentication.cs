

using System.ComponentModel.DataAnnotations;

namespace EMART_DAC.Models.DTO
{
    public class Authentication
    {
        [Key]
        public int Id { get; set; }

        public string CustEmail { get; set; }

        public string CustPassword { get; set; }

        public Authentication(string custEmail, string custPassword)
        {
            CustEmail = custEmail;
            CustPassword = custPassword;
        }

        public Authentication()
        {

        }
    }
}
