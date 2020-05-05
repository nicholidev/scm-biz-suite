
package com.doublechaintech.retailscm.candidatecontainer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.math.BigDecimal;
import com.doublechaintech.retailscm.BaseRowMapper;

public class CandidateContainerMapper extends BaseRowMapper<CandidateContainer>{
	
	protected CandidateContainer internalMapRow(ResultSet rs, int rowNumber) throws SQLException{
		CandidateContainer candidateContainer = getCandidateContainer();		
		 		
 		setId(candidateContainer, rs, rowNumber); 		
 		setName(candidateContainer, rs, rowNumber); 		
 		setVersion(candidateContainer, rs, rowNumber);

		return candidateContainer;
	}
	
	protected CandidateContainer getCandidateContainer(){
		return new CandidateContainer();
	}		
		
	protected void setId(CandidateContainer candidateContainer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String id = rs.getString(CandidateContainerTable.COLUMN_ID);
		
		if(id == null){
			//do nothing when nothing found in database
			return;
		}
		
		candidateContainer.setId(id);
	}
		
	protected void setName(CandidateContainer candidateContainer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		String name = rs.getString(CandidateContainerTable.COLUMN_NAME);
		
		if(name == null){
			//do nothing when nothing found in database
			return;
		}
		
		candidateContainer.setName(name);
	}
		
	protected void setVersion(CandidateContainer candidateContainer, ResultSet rs, int rowNumber) throws SQLException{
	
		//there will be issue when the type is double/int/long
		
		Integer version = rs.getInt(CandidateContainerTable.COLUMN_VERSION);
		
		if(version == null){
			//do nothing when nothing found in database
			return;
		}
		
		candidateContainer.setVersion(version);
	}
		
		

}


