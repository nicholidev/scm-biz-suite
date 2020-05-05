
package com.doublechaintech.retailscm.memberrewardpointredemption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;
import com.doublechaintech.retailscm.retailstoremember.RetailStoreMember;

public class MemberRewardPointRedemptionMapper extends BaseRowMapper<MemberRewardPointRedemption>{
	
	protected MemberRewardPointRedemption internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		MemberRewardPointRedemption memberRewardPointRedemption = getMemberRewardPointRedemption();		
		 		
 		setId(memberRewardPointRedemption, rs, rowNumber); 		
 		setName(memberRewardPointRedemption, rs, rowNumber); 		
 		setPoint(memberRewardPointRedemption, rs, rowNumber); 		
 		setOwner(memberRewardPointRedemption, rs, rowNumber); 		
 		setVersion(memberRewardPointRedemption, rs, rowNumber);

		return memberRewardPointRedemption;
	}
	
	protected MemberRewardPointRedemption getMemberRewardPointRedemption(){
		return new MemberRewardPointRedemption();
	}		
		
	protected void setId(MemberRewardPointRedemption memberRewardPointRedemption, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(MemberRewardPointRedemptionTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		memberRewardPointRedemption.setId(id);
	}
		
	protected void setName(MemberRewardPointRedemption memberRewardPointRedemption, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(MemberRewardPointRedemptionTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		memberRewardPointRedemption.setName(name);
	}
		
	protected void setPoint(MemberRewardPointRedemption memberRewardPointRedemption, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer point = rs.getInt(MemberRewardPointRedemptionTable.COLUMN_POINT);
		
		if(point == null){
			//do nothing when nothing found in database
			return;
		}
		
		memberRewardPointRedemption.setPoint(point);
	}
		 		
 	protected void setOwner(MemberRewardPointRedemption memberRewardPointRedemption, ResultSet rs, int rowNumber) throws SQLException{
 		String retailStoreMemberId = rs.getString(MemberRewardPointRedemptionTable.COLUMN_OWNER);
 		if( retailStoreMemberId == null){
 			return;
 		}
 		if( retailStoreMemberId.isEmpty()){
 			return;
 		}
 		RetailStoreMember retailStoreMember = memberRewardPointRedemption.getOwner();
 		if( retailStoreMember != null ){
 			//if the root object 'memberRewardPointRedemption' already have the property, just set the id for it;
 			retailStoreMember.setId(retailStoreMemberId);
 			
 			return;
 		}
 		memberRewardPointRedemption.setOwner(createEmptyOwner(retailStoreMemberId));
 	}
 	
	protected void setVersion(MemberRewardPointRedemption memberRewardPointRedemption, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(MemberRewardPointRedemptionTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		memberRewardPointRedemption.setVersion(version);
	}
		
		

 	protected RetailStoreMember  createEmptyOwner(String retailStoreMemberId){
 		RetailStoreMember retailStoreMember = new RetailStoreMember();
 		retailStoreMember.setId(retailStoreMemberId);
 		retailStoreMember.setVersion(Integer.MAX_VALUE);
 		return retailStoreMember;
 	}
 	
}


