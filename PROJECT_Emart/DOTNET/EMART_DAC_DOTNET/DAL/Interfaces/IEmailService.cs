using EMART_DAC.Models.DTO;

namespace EMART_DAC.DAL.Interfaces
{
    public interface IEmailService
    {
        Task<string> SendSimpleMail(EmailMaster details);
        Task<string> SendMailWithAttachment(EmailMaster details);
    }
}
