package de.ptb.length.involute

import de.ptb.length.check.ICheckable


/**
 * @description
 * *
 * @create 2017-06-15 上午10:25
 * *
 * @email spartajet.guo@gmail.com
 */
interface IAnglePressureNormal : IAngle, ICheckable {
    fun calculateValue(angleHelix: IAngle, anglePressure: IAngle, teethNumber: TeethNumber, moduleNormal: ModuleNormal, diameterBase: DiameterBase, moduleBasic: ModuleBasic, moduleTransverse: ModuleTransverse)

    fun calculateContradiction(angleHelix: IAngle, anglePressure: IAngle, teethNumber: TeethNumber, moduleNormal: ModuleNormal, diameterBase: DiameterBase, moduleBasic: ModuleBasic, moduleTransverse: ModuleTransverse)
}
