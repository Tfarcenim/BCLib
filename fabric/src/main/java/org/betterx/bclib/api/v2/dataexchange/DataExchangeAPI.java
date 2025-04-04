package org.betterx.bclib.api.v2.dataexchange;

import org.betterx.bclib.api.v2.dataexchange.handler.DataExchange;



public class DataExchangeAPI extends DataExchange {
    /**
     * You should never need to create a custom instance of this Object.
     */
    public DataExchangeAPI() {
        super();
    }

    
    protected ConnectorClientside clientSupplier(DataExchange api) {
        return new ConnectorClientside(api);
    }

    protected ConnectorServerside serverSupplier(DataExchange api) {
        return new ConnectorServerside(api);
    }
}
