namespace EMART_DAC.Models.DTO
{
    public class EmailMaster
    {
        private string recipient = "";
        private string msgBody = "Your order is Placed";
        private string name;
        private string useremail;
        private string attachment;

        public string Recipient
        {
            get { return recipient; }
            set { recipient = value; }
        }

        public string MsgBody
        {
            get { return msgBody; }
            set { msgBody = value; }
        }

        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        public string Useremail
        {
            get { return useremail; }
            set { useremail = value; }
        }

        public string Attachment
        {
            get { return attachment; }
            set { attachment = value; }
        }

        public EmailMaster(string recipient, string msgBody, string name, string useremail, string attachment)
        {
            this.recipient = recipient;
            this.msgBody = msgBody;
            this.name = name;
            this.useremail = useremail;
            this.attachment = attachment;
        }

        public EmailMaster()
        {

        }

        public override string ToString()
        {
            return "EmailMaster [recipient=" + recipient + ", msgBody=" + msgBody + ", name=" + name + ", useremail="
                + useremail + ", attachment=" + attachment + "]";
        }
    }
}
