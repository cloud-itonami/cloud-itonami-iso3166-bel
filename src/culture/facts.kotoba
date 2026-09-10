(ns culture.facts
  "Country-level regional-culture catalog for Belgium (BEL) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"BEL"
   [{:culture/id "bel.dish.moules-frites"
     :culture/name "Moules-frites"
     :culture/country "BEL"
     :culture/kind :dish
     :culture/summary "Main dish of mussels and fries originating in Northern France and Belgium, the national dish of Belgium."
     :culture/url "https://en.wikipedia.org/wiki/Moules-frites"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bel.dish.belgian-waffle"
     :culture/name "Belgian waffle"
     :culture/country "BEL"
     :culture/kind :dish
     :culture/summary "Waffle variety from Belgium with lighter batter, larger squares and deeper pockets; within Belgium there are several kinds, including the Brussels waffle and the Liège waffle."
     :culture/url "https://en.wikipedia.org/wiki/Belgian_waffle"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bel.dish.flemish-stew"
     :culture/name "Flemish stew"
     :culture/name-local "Stoofvlees / carbonade flamande"
     :culture/country "BEL"
     :culture/kind :dish
     :culture/summary "Beef (or pork) stew braised in beer, originating from Belgium and popular in Belgium, the Netherlands and French Flanders."
     :culture/url "https://en.wikipedia.org/wiki/Flemish_stew"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bel.product.belgian-chocolate"
     :culture/name "Belgian chocolate"
     :culture/country "BEL"
     :culture/kind :product
     :culture/summary "Chocolate produced in Belgium, a major industry since the 19th century with over 2,000 chocolatiers; the soft-centred praline was an invention of the Belgian chocolate industry."
     :culture/url "https://en.wikipedia.org/wiki/Belgian_chocolate"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bel.beverage.belgian-beer"
     :culture/name "Belgian beer"
     :culture/country "BEL"
     :culture/kind :beverage
     :culture/summary "Belgium's beer culture spans Trappist and abbey breweries, lambics and more than 1,600 beers; UNESCO inscribed Belgian beer culture on the intangible cultural heritage list in 2016."
     :culture/url "https://en.wikipedia.org/wiki/Beer_in_Belgium"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bel.festival.carnival-of-binche"
     :culture/name "Carnival of Binche"
     :culture/name-local "Carnaval de Binche"
     :culture/country "BEL"
     :culture/kind :festival
     :culture/summary "Annual pre-Lenten carnival in Binche, Hainaut Province, famous for its costumed Gilles, proclaimed a UNESCO Masterpiece of the Oral and Intangible Heritage of Humanity in 2003."
     :culture/url "https://en.wikipedia.org/wiki/Carnival_of_Binche"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "bel.heritage.grand-place"
     :culture/name "Grand-Place"
     :culture/name-local "Grote Markt"
     :culture/country "BEL"
     :culture/kind :heritage
     :culture/summary "Central square of Brussels surrounded by Baroque guildhalls, the Town Hall and the King's House, a UNESCO World Heritage Site since 1998."
     :culture/url "https://en.wikipedia.org/wiki/Grand-Place"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-bel culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "BEL"))
                 " BEL entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
