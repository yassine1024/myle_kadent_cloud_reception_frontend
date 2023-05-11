package src.presenters;

import src.cabinet.Cabinet;
import src.cabinet.CabinetService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePresenter {


    private final CabinetService cabinetService;


    public HomePresenter(CabinetService cabinetService) {
        this.cabinetService = cabinetService;
    }

    public void getCabinet(String id) {
        cabinetService.getCabinet(new Callback<Cabinet>() {
            @Override
            public void onResponse(Call<Cabinet> call, Response<Cabinet> response) {
                if (response.isSuccessful()) {
                    Cabinet cabinet = response.body();
                    System.out.println("Fares: "+cabinet.getId());
                    System.out.println("Fares: "+cabinet.getName());
                    System.out.println("Fares: "+cabinet.getPhoneNumber());
//                    view.showCabinet(cabinet);
                } else {
//                    view.showError("Error getting cabinet");
                }
            }

            @Override
            public void onFailure(Call<Cabinet> call, Throwable t) {
//                view.showError("Error getting cabinet: " + t.getMessage());
            }
        }, id);
    }
}
