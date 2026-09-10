(ns statute.facts
  "General-law compliance catalog for Belgium (BEL) -- extends this
  repo's existing `marketentry.facts` (public-procurement market-entry
  only, narrow scope) with a second, orthogonal catalog of statutes a
  company generally must track for compliance. Mirrors
  cloud-itonami-iso3166-jpn/-usa/-esp/-swe/-nor/-dnk/-fin/-prt's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-federation).

  Every entry cites an OFFICIAL ejustice.just.fgov.be (Moniteur belge /
  Belgisch Staatsblad, Belgium's official gazette / Justel consolidated
  law database) URL -- never fabricated. A law not in this table has NO
  spec-basis, full stop; extend `catalog`, do not invent an id/url.
  ejustice.just.fgov.be rendered directly to WebFetch. NOTE: this
  catalog required 2 abandoned countries in the same tick before landing
  on Belgium -- Austria's ris.bka.gv.at returned HTTP 503 across every
  URL form tried (a bot-protection block, not a JS-only rendering
  issue), and Poland's isap.sejm.gov.pl is CAPTCHA-gated (never
  attempted to bypass, per this project's safety floor). Both dead-ends
  are recorded in ADR-2607141700's addenda rather than silently
  discarded.")

(def catalog
  "iso3 -> vector of statute entries."
  {"BEL"
   [{:statute/id "bel.code-des-societes-et-des-associations-2019"
     :statute/title "Code des sociétés et des associations (Wetboek van vennootschappen en verenigingen)"
     :statute/jurisdiction "BEL"
     :statute/kind :law
     :statute/law-number "23 mars 2019"
     :statute/url "https://www.ejustice.just.fgov.be/cgi_loi/change_lg.pl?language=fr&la=F&cn=2019032309&table_name=loi"
     :statute/url-provenance :official-ejustice-fgov-be
     :statute/enacted-date "2019-03-23"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "bel.loi-protection-donnees-2018"
     :statute/title "Loi relative à la protection des personnes physiques à l'égard des traitements de données à caractère personnel"
     :statute/jurisdiction "BEL"
     :statute/kind :law
     :statute/law-number "30 juillet 2018"
     :statute/url "https://www.ejustice.just.fgov.be/eli/loi/2018/07/30/2018040581/justel"
     :statute/url-provenance :official-ejustice-fgov-be
     :statute/enacted-date "2018-07-30"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "bel.loi-contrats-de-travail-1978"
     :statute/title "Loi relative aux contrats de travail"
     :statute/jurisdiction "BEL"
     :statute/kind :law
     :statute/law-number "3 juillet 1978"
     :statute/url "https://www.ejustice.just.fgov.be/eli/loi/1978/07/03/1978070303/justel"
     :statute/url-provenance :official-ejustice-fgov-be
     :statute/enacted-date "1978-07-03"
     :statute/retrieved-at "2026-07-15"
     :statute/topic #{:labor :employment}}]})

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
      :note (str "cloud-itonami-iso3166-bel statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "BEL")) " BEL statutes seeded with an "
                 "official ejustice.just.fgov.be citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
