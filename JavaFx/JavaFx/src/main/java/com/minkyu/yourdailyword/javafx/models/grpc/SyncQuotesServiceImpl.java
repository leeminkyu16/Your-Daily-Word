package com.minkyu.yourdailyword.javafx.models.grpc;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.common.protos.*;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import io.grpc.stub.StreamObserver;

public class SyncQuotesServiceImpl extends SyncQuotesServiceGrpc.SyncQuotesServiceImplBase {
	private final IQuotesManager quotesManager;

	@Inject
	public SyncQuotesServiceImpl(
		IQuotesManager quotesManager
	) {
		super();
		this.quotesManager = quotesManager;
	}

	@Override
	public void getDeviceName(
		GetDeviceNameRequest request,
		StreamObserver<GetDeviceNameResponse> responseObserver
	) {
		responseObserver.onNext(
			GetDeviceNameResponse
				.newBuilder()
				.setSenderDeviceId(request.getSenderDeviceId())
				.setReceiverDeviceId("")
				.build()
		);
		responseObserver.onCompleted();
	}

	@Override
	public void syncQuotes(
		SyncQuotesRequest request,
		StreamObserver<SyncQuotesResponse> responseObserver
	) {
		Quotes localQuotes = quotesManager.getProto();
		Quotes remoteQuotes = request.getQuotes();

		if (remoteQuotes.getLastModified() > localQuotes.getLastModified()) {
			this.quotesManager.setFromProto(remoteQuotes);

			responseObserver.onNext(
				SyncQuotesResponse
					.newBuilder()
					.setQuotes(
						remoteQuotes
					)
					.setUpdate(false)
					.build()
			);
		}
		else if (remoteQuotes.getLastModified() < localQuotes.getLastModified()) {
			responseObserver.onNext(
				SyncQuotesResponse
					.newBuilder()
					.setQuotes(
						localQuotes
					)
					.setUpdate(true)
					.build()
			);
		}
		else if (remoteQuotes.getValuesList().size() > localQuotes.getValuesList().size()) {
			this.quotesManager.setFromProto(remoteQuotes);

			responseObserver.onNext(
				SyncQuotesResponse
					.newBuilder()
					.setQuotes(
						remoteQuotes
					)
					.setUpdate(false)
					.build()
			);
		}
		else {
			responseObserver.onNext(
				SyncQuotesResponse
					.newBuilder()
					.setQuotes(
						localQuotes
					)
					.setUpdate(true)
					.build()
			);
		}

		responseObserver.onCompleted();
	}
}
