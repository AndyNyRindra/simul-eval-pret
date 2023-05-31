<div class="modal fade" tabindex="-1" id="restore-modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Restaurer</h3>

                <!--begin::Close-->
                <div class="btn btn-icon btn-sm btn-active-light-primary ms-2" data-bs-dismiss="modal" aria-label="Close">
                    <i class="fa-solid fa-xmark fs-1"></i>
                </div>
                <!--end::Close-->
            </div>

            <div class="modal-body">
                <p>Etes-vous sur de vouloir restaurer cette demande de <span id="restore-name"></span> de Id <span id="restore-id"></span> ?</p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-bs-dismiss="modal">Annuler</button>
                <a class="btn btn-primary" id="restore-url">Restaurer</a>
            </div>
        </div>
    </div>
</div>