(ns marketentry.facts "Belgium market-entry catalog.")
(def catalog
  {"BEL" {:name "Belgium"
          :owner-authority "FPS BOSA / e-Procurement"
          :legal-basis "Public Procurement Act; EU directives"
          :national-spec "e-Procurement platform + CBE/BCE number"
          :provenance "https://www.publicprocurement.be/"
          :required-evidence ["CBE/BCE number record"
                              "e-Procurement registration record"
                              "Belgian company extract"
                              "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / FPS BOSA"
          :rep-legal-basis "EU establishment or authorized representative for many procedures"
          :rep-provenance "https://www.publicprocurement.be/"
          :corporate-number-owner-authority "FPS Economy / Crossroads Bank for Enterprises"
          :corporate-number-legal-basis "CBE/BCE enterprise number"
          :corporate-number-provenance "https://kbopub.economie.fgov.be/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR"
          :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "NLD" {:name "Netherlands" :owner-authority "TenderNed" :legal-basis "Aanbestedingswet"
          :national-spec "TenderNed" :provenance "https://www.tenderned.nl/"
          :required-evidence ["KvK extract" "TenderNed registration" "BTW record" "Authorized-representative record"]}
   "FRA" {:name "France" :owner-authority "PLACE" :legal-basis "Code de la commande publique"
          :national-spec "PLACE" :provenance "https://www.marches-publics.gouv.fr/"
          :required-evidence ["SIRET record" "PLACE registration" "RCS extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
