//package com.example.hrms.core.adapters;
//
//import com.example.hrms.entities.concretes.JobSeeker;
//
//
//import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;
//
//public class MernisAdapter implements CustomerCheckService{
//
//	@Override
//	public boolean chekIfRealPerson(JobSeeker jobSeeker) {
//		KPSPublicSoapProxy client=new KPSPublicSoapProxy();
//		boolean result=true;
//		try {
//			result=client.TCKimlikNoDogrula(
//					Long.parseLong(jobSeeker.getIdentityNumber()),
//					jobSeeker.getFirstName().toUpperCase()	,
//					jobSeeker.getLastName().toUpperCase(), 
//					jobSeeker.getDateOfBirth().getYear());
//		} catch (Exception e) {
//			return result=false;
//		}
//		
//		
//	 	 
//		
//		return result;
//	}
//	
//}
