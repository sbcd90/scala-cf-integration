package bootstrap.liftweb

import net.liftweb.http.LiftRules
import net.liftweb.sitemap.{Menu, SiteMap}

class Boot {

  def boot: Unit = {
    LiftRules.addToPackages("com.sap.cf")
    LiftRules.setSiteMap(SiteMap(Menu("Home") / "index"))
  }
}