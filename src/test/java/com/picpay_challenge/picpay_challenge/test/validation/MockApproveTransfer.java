package com.picpay_challenge.picpay_challenge.test.validation;

import com.picpay_challenge.picpay_challenge.core.validation.ApproveTransfer;

public class MockApproveTransfer implements ApproveTransfer {
	@Override
	public boolean confirm() {
		return true;
	}

}
