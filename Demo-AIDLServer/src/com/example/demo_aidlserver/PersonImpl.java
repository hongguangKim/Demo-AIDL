package com.example.demo_aidlserver;

import android.os.RemoteException;
import android.com.personservice.IPerson;

public class PersonImpl extends IPerson.Stub {
	private String name;

	@Override
	public String getName() throws RemoteException {
		return name;
	}

	@Override
	public void setName(String name) throws RemoteException {
		this.name = name;
	}
}
