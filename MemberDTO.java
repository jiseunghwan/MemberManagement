package Prosess;

import java.util.Formatter;

public class MemberDTO {
 
    private String no;
    private String name;
    private String ssn;
    private String phoneNum;
	private String address;
	private String email;
	private String sex;
   
    //기본생성자
    public MemberDTO() {
   
    }
   
    //생성자
    public MemberDTO(String name, String ssn, String phoneNum, String address, String email, String sex) {
        this.name = name;
        this.ssn = ssn;
        this.phoneNum = phoneNum;
        this.address= address;
        this.email = email;
        this.sex = sex;
    }
   
   
    //생성자
    public MemberDTO(String no, String name, String ssn, String phoneNum,
            String address, String email, String sex) {
        super();
        this.no = no;
        this.name = name;
        this.ssn = ssn;
        this.phoneNum = phoneNum;
        this.address = address;
        this.email = email;
        this.sex = sex;
       
    }
 
    //getter, setter
    public String getNo() {
        return no;
    }
 
    public void setNo(String no) {
        this.no = no;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getSsn() {
        return ssn;
    }
 
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
 
    public String getPhoneNum() {
        return phoneNum;
    }
 
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
 
 
    @Override
    public String toString() {
        Formatter fm = new Formatter();
        String meminfo = fm.format("%5s\t %3s\t %10s\t %-7s\t %-7s\t %-7s\t %-7s\t", no, name,ssn, phoneNum,address, email, sex).toString();
        return meminfo;
    }
   
 
    public String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n");
        sb.append("[ "+no+ " ] 회원님의 정보====\n\n");
        sb.append("이름 : "+name+"\n");
        sb.append("생년월일 : "+ssn+"\n");
        sb.append("전화번호 : "+phoneNum+"\n");
        sb.append("주소 : "+address+"\n");
        sb.append("이메일 : "+email+"\n");
        sb.append("성별 : "+sex+"\n\n");
        sb.append("========================\n");
               
        return sb.toString();
    }
 
}
