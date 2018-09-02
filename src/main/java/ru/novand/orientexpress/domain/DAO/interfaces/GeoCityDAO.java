package ru.novand.orientexpress.domain.DAO.interfaces;

import ru.novand.orientexpress.domain.dto.MapPointDTO;

import java.util.List;

public interface GeoCityDAO {
    public List<MapPointDTO> getAllGeoCityByCities(List<String> citylist);
}
