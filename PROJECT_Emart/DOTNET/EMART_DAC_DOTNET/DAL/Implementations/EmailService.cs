using EMART_DAC.DAL.Interfaces;
using EMART_DAC.Models.DTO;
using Microsoft.Extensions.Options;
using MimeKit;
using System.Net.Mail;
using MailKit.Net.Smtp;
using SmtpClient = MailKit.Net.Smtp.SmtpClient;

namespace EMART_DAC.DAL.Implementations
{
    public class EmailService : IEmailService
    {
        private readonly SmtpSettings _smtpSettings;

        public EmailService(IOptions<SmtpSettings> smtpSettings)
        {
            _smtpSettings = smtpSettings.Value;
        }

        public async Task<string> SendSimpleMail(EmailMaster details)
        {
            try
            {
                var message = new MimeMessage();
                message.From.Add(MailboxAddress.Parse(_smtpSettings.Username));
                message.To.Add(MailboxAddress.Parse(details.Recipient));
                message.Subject = "New Enquiry of : " + details.Name + " : " + details.Useremail;
                message.Body = new TextPart("plain") { Text = details.MsgBody };

                using (var client = new MailKit.Net.Smtp.SmtpClient())
                {
                    await client.ConnectAsync(_smtpSettings.Host, _smtpSettings.Port, true);
                    await client.AuthenticateAsync(_smtpSettings.Username, _smtpSettings.Password);
                    await client.SendAsync(message);
                    await client.DisconnectAsync(true);
                }
                return "Mail Sent Successfully...";
            }
            catch (Exception e)
            {
                return "Error while Sending Mail";
            }
        }

        public async Task<string> SendMailWithAttachment(EmailMaster details)
        {
            try
            {
                var message = new MimeMessage();
                message.From.Add(MailboxAddress.Parse(_smtpSettings.Username));
                message.To.Add(MailboxAddress.Parse(details.Recipient));
                message.Subject = details.Name + details.Useremail;

                var builder = new BodyBuilder { TextBody = details.MsgBody };

                // Add attachment
                builder.Attachments.Add(details.Attachment);

                message.Body = builder.ToMessageBody();

                using (var client = new MailKit.Net.Smtp.SmtpClient())
                {
                    await client.ConnectAsync(_smtpSettings.Host, _smtpSettings.Port, true);
                    await client.AuthenticateAsync(_smtpSettings.Username, _smtpSettings.Password);
                    await client.SendAsync(message);
                    await client.DisconnectAsync(true);
                }
                return "Mail sent Successfully";
            }
            catch (Exception e)
            {
                return "Error while sending mail!!!";
            }
        }
    }
}