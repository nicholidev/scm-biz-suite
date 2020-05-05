
package com.doublechaintech.retailscm.memberrewardpoint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.retailstoremember.RetailStoreMember;

public class MemberRewardPointMapper extends BaseRowMapper<MemberRewardPoint>{
	
	protected MemberRewardPoint internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		MemberRewardPoint memberRewardPoint = getMemberRewardPoint();		
		 		
 		setId(memberRewardPoint, rs, rowNumber); 		
 		setName(memberRewardPoint, rs, rowNumber); 		
 		setPoint(memberRewardPoint, rs, rowNumber); 		
 		setOwner(memberRewardPoint, rs, rowNumber); 		
 		setVersion(memberRewardPoint, rs, rowNumber);

		return memberRewardPoint;
	}
	
	protected MemberRewardPoint getMemberRewardPoint(){
		return new MemberRewardPoint();
	}		
		
	protected void setId(MemberRewardPoint memberRewardPoint, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(MemberRewardPointTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		memberRewardPoint.setId(id);
	}
		
	protected void setName(MemberRewardPoint memberRewardPoint, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(MemberRewardPointTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		memberRewardPoint.setName(name);
	}
		
	protected void setPoint(MemberRewardPoint memberRewardPoint, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer point = rs.getInt(MemberRewardPointTable.COLUMN_POINT);
		
		if(point == null){
			//do nothing when nothing found in database
			return;
		}
		
		memberRewardPoint.setPoint(point);
	}
		 		
 	protected void setOwner(MemberRewardPoint memberRewardPoint, ResultSet rs, int rowNumber) throws SQLException{
 		String retailStoreMemberId = rs.getString(MemberRewardPointTable.COLUMN_OWNER);
 		if( retailStoreMemberId == null){
 			return;
 		}
 		if( retailStoreMemberId.isEmpty()){
 			return;
 		}
 		RetailStoreMember retailStoreMember = memberRewardPoint.getOwner();
 		if( retailStoreMember != null ){
 			//if the root object 'memberRewardPoint' already have the property, just set the id for it;
 			retailStoreMember.setId(retailStoreMemberId);
 			
 			return;
 		}
 		memberRewardPoint.setOwner(createEmptyOwner(retailStoreMemberId));
 	}
 	
	protected void setVersion(MemberRewardPoint memberRewardPoint, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(MemberRewardPointTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		memberRewardPoint.setVersion(version);
	}
		
		

 	protected RetailStoreMember  createEmptyOwner(String retailStoreMemberId){
 		RetailStoreMember retailStoreMember = new RetailStoreMember();
 		retailStoreMember.setId(retailStoreMemberId);
 		retailStoreMember.setVersion(Integer.MAX_VALUE);
 		return retailStoreMember;
 	}
 	
}


