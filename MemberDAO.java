package Prosess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
public class MemberDAO {
   
    private static Connection conn;
   
    private PreparedStatement pstmt;
    private CallableStatement cstmt;
    private ResultSet rs;
   
   
    //기본생성자
    public MemberDAO() {
   
    }
       
    private void getConnection() throws ClassNotFoundException, SQLException{
        if(conn == null){ //dbConn이 null이면 Connection 객체 얻어오기..
            //접속정보
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "madang";
            String pw = "madang";
           
            //JDBC드라이버 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");
           
            //오라클(DBMS)에 연결하여 Connection 객체 얻기.
            conn = DriverManager.getConnection(url,user,pw);
                               
        }
    }
   
       
    /**
    * 회원 등록하기
    */
    public boolean insertMember(MemberDTO dto){    
       
        boolean result = false;            
        try {
            getConnection();
           
            String sql = "INSERT INTO MANAGEMENT VALUES (LPAD(seq_management_no.nextval,4,'0'),:name,:ssn,:phoneNum,:address,:email,:sex)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
           
            pstmt.setString(1,dto.getName());
            pstmt.setString(2,dto.getSsn());
            pstmt.setString(3,dto.getPhoneNum());
            pstmt.setString(4,dto.getAddress());
            pstmt.setString(5,dto.getEmail());
            pstmt.setString(6,dto.getSex());
           
            int r = pstmt.executeUpdate();
           
            if(r>0) result = true;
           
        } catch (Exception e) {
            System.out.println("예외발생:insertMember()=> "+e.getMessage());
        }finally{          
            dbClose();
        }      
        return result;
    }      
   
    /**
    * 회원번호에 해당하는 회원정보 보기
    */
    public MemberDTO getMember(String no){
        MemberDTO dto =null;
        try {
            getConnection();
           
            String sql = "SELECT m_no, m_name, m_ssn, m_phoneNum, m_address, m_email, m_sex FROM MANAGEMENT WHERE m_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, no);
            ResultSet r = pstmt.executeQuery();
           
            if(r.next()) {
                String m_no = r.getString("m_no");
                String m_name = r.getString("m_name");
                String m_ssn = r.getString("m_ssn");
                String m_phoneNum = r.getString("m_phoneNum");
                String m_address = r.getString("m_address");
                String m_email = r.getString("m_email");
                String m_sex = r.getString("m_sex");
                dto = new MemberDTO(m_no, m_name, m_ssn, m_phoneNum, m_address, m_email, m_sex);
            }
           
        } catch (Exception e) {
            System.out.println("예외발생:deleteMember()=> "+e.getMessage());
        }finally{          
            dbClose();
        }      
       
        return dto;
    }
 
    /**
    * 저장된 회원 목록 보기
    */
    public List<MemberDTO> getMemberList(){
        List<MemberDTO> list = new ArrayList<MemberDTO>();
       
        try {
            getConnection();
           
            String sql = "SELECT m_no, m_name, m_ssn, m_phoneNum, m_address, m_email, m_sex FROM MANAGEMENT  ORDER BY m_no ASC";
 
            PreparedStatement pstmt = conn.prepareStatement(sql);          
            ResultSet r = pstmt.executeQuery();
           
            while(r.next()) {
                String m_no = r.getString("m_no");
                String m_name = r.getString("m_name");
                String m_ssn = r.getString("m_ssn");
                String m_phoneNum = r.getString("m_phoneNum");
                String m_address = r.getString("m_address");
                String m_email = r.getString("m_email");
                String m_sex = r.getString("m_sex");
                list.add(new MemberDTO(m_no, m_name, m_ssn, m_phoneNum, m_address, m_email, m_sex));
            }
           
        } catch (Exception e) {
            System.out.println("예외발생:getMemberList()=> "+e.getMessage());
        }finally{          
            dbClose();
        }  
       
        return list;
    }
   
 
    /**
    * 회원 수정
    */
    public boolean updateMember(MemberDTO dto){
       
        boolean result = false;            
        try {
            getConnection();
           
            String sql = "UPDATE MANAGEMENT SET m_name=:name , m_ssn=:ssn , m_phoneNum=:phoneNum , m_address=:address , m_email=:email , m_sex=:sex WHERE m_no=:no";
            PreparedStatement pstmt = conn.prepareStatement(sql);
           
            pstmt.setString(1,dto.getName());
            pstmt.setString(2,dto.getSsn());
            pstmt.setString(3,dto.getPhoneNum());
            pstmt.setString(4,dto.getAddress());
            pstmt.setString(5,dto.getEmail());
            pstmt.setString(6,dto.getSex());
            pstmt.setString(7,dto.getNo());        
           
            int r = pstmt.executeUpdate();
           
            if(r>0) result = true;
           
        } catch (Exception e) {
            System.out.println("예외발생:updateMember()=> "+e.getMessage());
        }finally{          
            dbClose();
        }      
        return result;
    }
   
   
    /**
    * 회원 삭제
    */
    public boolean deleteMember(String id){        
        boolean result = false;            
        try {
            getConnection();
           
            String sql = "DELETE FROM MANAGEMENT WHERE m_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            int r = pstmt.executeUpdate();
           
            if(r>0) result = true;
           
        } catch (Exception e) {
            System.out.println("예외발생:deleteMember()=> "+e.getMessage());
        }finally{          
            dbClose();
        }      
        return result;
    }//deleteMember()--------------
   
   
    /**DB연결 해제(닫기)*/
    public void dbClose(){      
     
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("예외:ResultSet객체 close():" + e.getMessage());
            }
        }
         
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("예외:PreparedStatement객체 close():" + e.getMessage());
            }
        }
       
        if (cstmt != null) {
            try {
                cstmt.close();
            } catch (SQLException e) {
                System.out.println("예외:CallableStatement객체 close():" + e.getMessage());
            }
        }      
       
           
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("예외:Connection객체 close():" + e.getMessage());
            }
        }    
         
        conn = null;        
    }//dbClose()---------
   
}
